
---

# Blackjack Game by Blake Vieyra

## Description

This is a web-based Blackjack game implemented using HTML, CSS, JavaScript, and Spring Boot framework for the backend. The game allows users to play Blackjack against a computer dealer. It provides features such as hitting, standing, starting a new game, and viewing game statistics.

## Technologies Used

- **Frontend:**
  - HTML: Markup language for structuring the web pages.
  - CSS: Styling language for enhancing the visual presentation.
  - JavaScript: Programming language for implementing game logic and interactivity.
  - Fetch API: Used for making asynchronous HTTP requests to the backend server.
  - AJAX: Asynchronous JavaScript and XML for dynamically updating web content.
  
- **Backend:**
  - Spring Boot: Framework for building Java-based web applications.
  - Java: Programming language used for backend development.
  - RESTful API: Used for communication between the frontend and backend.

## API Calls

### 1. Start a New Game

- **URL:** `/api/blackjack/start`
- **Method:** POST
- **Description:** Initiates a new game of Blackjack.
- **Request Body:** None
- **Response Body:**
  ```json
  {
    "message": "Start",
    "cardDetailsDealer": ["Ace of Spades", "7 of Hearts"],
    "cardDetailsPlayer": ["King of Diamonds", "5 of Clubs"]
  }
  ```

### 2. Hit

- **URL:** `/api/blackjack/hit`
- **Method:** POST
- **Description:** Requests to receive an additional card during the game.
- **Request Body:** None
- **Response Body:**
  ```json
  {
    "message": "Hit",
    "cardDetailsDealer": ["Ace of Spades", "7 of Hearts"],
    "cardDetailsPlayer": ["King of Diamonds", "5 of Clubs", "3 of Spades"],
    "status": "Continue"
  }
  ```

### 3. Stand

- **URL:** `/api/blackjack/stand`
- **Method:** POST
- **Description:** Signals that the player has chosen to end their turn.
- **Request Body:** None
- **Response Body:**
  ```json
  {
    "message": "Stand",
    "cardDetailsDealer": ["Ace of Spades", "7 of Hearts", "10 of Diamonds"],
    "cardDetailsPlayer": ["King of Diamonds", "5 of Clubs", "3 of Spades"],
    "status": "Win"
  }
  ```

### 4. Get Game Status

- **URL:** `/api/blackjack/status`
- **Method:** GET
- **Description:** Retrieves the current status of the game.
- **Response Body:**
  ```json
  {
    "message": "Continue",
    "cardDetailsDealer": ["Ace of Spades", "7 of Hearts", "10 of Diamonds"],
    "cardDetailsPlayer": ["King of Diamonds", "5 of Clubs", "3 of Spades"]
  }
  ```

### 5. Get Scores

- **URL:** `/api/blackjack/scores`
- **Method:** GET
- **Description:** Retrieves the current scores of the player and dealer.
- **Response Body:**
  ```json
  {
    "playerWins": 3,
    "dealerWins": 5
  }
  ```

### 6. Get Total Points

- **URL:** `/api/blackjack/total`
- **Method:** GET
- **Description:** Retrieves the total points of the player and dealer.
- **Response Body:**
  ```json
  {
    "playerTotal": 18,
    "dealerTotal": 20
  }
  ```

## AJAX

The game uses AJAX (Asynchronous JavaScript and XML) to communicate with the backend server without reloading the entire web page. This allows for dynamic updating of game status, scores, and card displays during gameplay.

## How to Initialize

1. **Clone the Repository:**
   ```
   git clone https://github.com/your-username/blackjack-game.git
   ```

2. **Navigate to the Project Directory:**
   ```
   cd blackjack-game
   ```

3. **Build and Run the Backend:**
   - Make sure you have Java and Maven installed.
   ```
   mvn spring-boot:run
   ```

4. **Open the Frontend:**
   - Open the `index.html` file in a web browser.
   ```
   /path/to/blackjack-game/frontend/index.html
   ```

5. **Start Playing:**
   - You can now play Blackjack using the web interface.

---

