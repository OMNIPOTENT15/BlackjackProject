const apiBaseUrl = '/api/blackjack';
const hitButton = document.getElementById('hitButton');
const standButton = document.getElementById('standButton');
const newGameButton = document.getElementById('newGameButton');
const statusMessageDiv = document.getElementById('status-messages');
const dealerTotalDiv = document.getElementById('dealer-total');
const playerTotalDiv = document.getElementById('player-total');
document.addEventListener('DOMContentLoaded', () => {
	hideControls();
});

hitButton.addEventListener('click', () => {
	playSound('cardSound');
	fetch(`api/blackjack/hit`, { method: 'POST' })
		.then(handleResponse)
		.then(data => {
			updateGameStatusAndScores(data);
			if (data.gameOver) {
				checkForWin(data); // Check if the player has won
				hideControls();
				newGameButton.style.display = 'block';
			}
		})
		.catch(handleError);
});

standButton.addEventListener('click', () => {
	playSound('cardSound');
	playSound('cardSound');
	fetch(`api/blackjack/stand`, { method: 'POST' })
		.then(handleResponse)
		.then(data => {
			checkForWin(data); // Check if the player has won
			updateGameStatusAndScores(data);
			hideControls();
			newGameButton.style.display = 'block';
		})
		.catch(handleError);
});

newGameButton.addEventListener('click', () => {
	playSound('cardSound');
	playBackgroundMusic();
	fetch(`api/blackjack/start`, { method: 'POST' })
		.then(handleResponse)
		.then(data => {
			checkForWin(data); // Check if the player has won
			clearCards();
			updateGameStatusAndScores(data);
			showControls();
			newGameButton.style.display = 'none';
		})
		.catch(handleError);
});

function playBackgroundMusic() {
	const musicPlayer = document.getElementById('background-music');
	if (musicPlayer) {
		musicPlayer.play().catch(e => {
			console.error('Error playing the music:', e);
		});
	}
}

function playSound(soundId) {
	const sound = document.getElementById(soundId);
	if (sound) {
		sound.playbackRate = 5.0; // Set playback speed to 400% of the original speed
		sound.play();
	}
}

function playWinSound() {
	const sound1 = document.getElementById('cardSound');
	sound1.pause();
	const sound = document.getElementById('winSound');
	if (sound) {
		sound.playbackRate = 2.0; // Set playback speed to 400% of the original speed
		sound.play();
	}
}

function handleError(error) {
	console.error('Fetch error:', error);
	alert('An error occurred. Please check the console for details.');
}

function handleResponse(response) {
	if (!response.ok) {
		throw new Error('Network response was not ok: ' + response.statusText);
	}
	return response.json().then(data => {
		console.log('Received JSON:', data);  // Log the data to see the structure
		return data;
	});
}

function updateGameStatusAndScores(data) {
	console.log('Received data:', data);
	statusMessageDiv.innerText = data.message;  // Display the game status message

	if (data.gameOver) {
		console.log('Game over:', data.message);
		clearCards(); // Clear the cards display if game is over
		hideControls(); // Hide game controls like hit and stand buttons
		newGameButton.style.display = 'block'; // Show the new game button to allow starting a new game
	}

	// Optionally update score displays if included in the response
	if (data.dealerScore !== undefined) {
		dealerTotalDiv.innerText = `Dealer: ${data.dealerScore}`;
	}
	if (data.playerScore !== undefined) {
		playerTotalDiv.innerText = `Player: ${data.playerScore}`;
	}

	updateCards('dealer-cards', data.dealerCards);
	updateCards('player-cards', data.playerCards);
}

function updateCards(elementId, cards) {
	const cardsDiv = document.getElementById(elementId);
	cardsDiv.innerHTML = '';
	if (cards && Array.isArray(cards)) {
		cards.forEach((card, index) => {
			const cardHTML = createCardHTML(card.value, card.suit);
			cardsDiv.innerHTML += cardHTML;
			const cardElement = cardsDiv.lastElementChild;
			cardElement.classList.add('fly-in');
		});
	} else {
		console.error('Invalid or missing card data:', cards);
	}
}

function createCardHTML(value, suit) {
	const suitSymbols = {
		"hearts": "&hearts;",   // â™¥
		"spades": "&spades;",   // â™ 
		"clubs": "&clubs;",     // â™£
		"diamonds": "&diams;"   // â™¦
	};
	const suitSymbol = suitSymbols[suit.toLowerCase()] || suit;
	return `<div class="card" style="display: inline-block; margin-right: 10px;">
                <span class="value">${value}</span>
                <span class="suit" style="color: ${suit === 'hearts' || suit === 'diamonds' ? 'red' : 'black'};">${suitSymbol}</span>
            </div>`;
}

function clearCards() {
	document.getElementById('dealer-cards').innerHTML = '';
	document.getElementById('player-cards').innerHTML = '';
}

function showControls() {
	hitButton.style.display = 'block';
	standButton.style.display = 'block';
}

function hideControls() {
	hitButton.style.display = 'none';
	standButton.style.display = 'none';
}
// Create a class to handle audio operations
class AudioManager {
	constructor() {
		this.audioCtx = new (window.AudioContext || window.webkitAudioContext)();
		this.sounds = {};
		this.allSoundsLoaded = false;
		this.loadSounds();
		this.ensureAudioContext();
	}

	ensureAudioContext() {
		if (this.audioCtx.state === 'suspended') {
			this.audioCtx.resume().then(() => {
				console.log('Playback resumed successfully');
			});
		}
	}

	loadSounds() {
		this.loadSound('cardSound', 'css/dealing.mp3');
		this.loadSound('winSound', 'css/win.mp3');
	}

	loadSound(name, url) {
		fetch(url)
			.then(response => response.arrayBuffer())
			.then(buffer => {
				this.audioCtx.decodeAudioData(buffer, decodedData => {
					this.sounds[name] = decodedData;
					this.checkAllSoundsLoaded();
				}, e => {
					console.error("Error decoding audio data for " + name, e);
				});
			})
			.catch(e => console.error("Failed to fetch sound from " + url, e));
	}

	checkAllSoundsLoaded() {
		const allSounds = ['cardSound', 'winSound']; // List all sound names
		this.allSoundsLoaded = allSounds.every(sound => this.sounds[sound] !== undefined);
		if (this.allSoundsLoaded) {
			console.log("All sounds loaded successfully.");
		}
	}

	playSound(name) {
		if (this.sounds[name] && this.allSoundsLoaded) {
			const soundSource = this.audioCtx.createBufferSource();
			const gainNode = this.audioCtx.createGain(); // Create gain node for volume control
			soundSource.buffer = this.sounds[name];

			if (name === 'cardSound') {
				soundSource.playbackRate.value = 5.0; // Increase speed for cardSound
			}

			if (name === 'winSound') {
				gainNode.gain.value = 0.5; // Reduce volume for winSound
			} else {
				gainNode.gain.value = 1.0; // Normal volume for other sounds
			}

			soundSource.connect(gainNode); // Connect source to gain node
			gainNode.connect(this.audioCtx.destination); // Connect gain node to destination
			soundSource.start(0);
		} else {
			console.error("Sound not loaded or all sounds not ready:", name);
		}
	}
}

// Initialize the AudioManager
const audioManager = new AudioManager();

function handleGameEvent(eventType) {
	switch (eventType) {
		case 'hit':
		case 'stand':
		case 'newGame':
			audioManager.playSound('cardSound');
			break;
		case 'win':
			audioManager.playSound('winSound');
			break;
		default:
			console.error('Unhandled game event type:', eventType);
	}
}

// Bind events to buttons
document.getElementById('hitButton').addEventListener('click', () => handleGameEvent('hit'));
document.getElementById('standButton').addEventListener('click', () => handleGameEvent('stand'));
document.getElementById('newGameButton').addEventListener('click', () => handleGameEvent('newGame'));

function checkForWin(data) {
	console.log(data); // Log the data to see what's being checked
	if (data.playerScore > 21) {
		statusMessageDiv.innerText = "Player busts! Dealer wins.";
	} else if (data.dealerScore > 21) {
		handleGameEvent('win');
		statusMessageDiv.innerText = "Dealer busts! Player wins.";
	} else if (data.playerScore === 21 && data.playerCards.length === 2 && data.dealerScore !== 21) {
		console.log("Blackjack condition met"); // Debug log
		handleGameEvent('win');
		statusMessageDiv.innerText = "Blackjack! Player wins.";
	} else if (data.gameOver) {
		console.log("Game over condition checked"); // Debug log
		if (data.playerScore > data.dealerScore) {
			handleGameEvent('win');
			statusMessageDiv.innerText = "Player wins with a higher score.";
		} else if (data.playerScore < data.dealerScore) {
			statusMessageDiv.innerText = "Dealer wins with a higher score.";
		} else {
			console.log("Tie condition met"); // Debug log
			statusMessageDiv.innerText = "It's a tie!";
		}
	}
}

