package application;
	


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;



public class Game21 extends Application {
	
	private int playerHits = 0;
	
	@FXML
	private Label card1;
	@FXML
	private Label card2;
	
	boolean[] usedCards = new boolean[11];
	
	private int playerTotal;
	
	@FXML
	private Label showPlayerTotal;
	private int playerCurrentTotal;
	
	@FXML
	private Label showComputerTotal;
	private int computerCurrentTotal;
	private int computerTotal;
	
	@FXML
	private Label compWin;
	private int computerWins;
	
	@FXML
	private Label computerNumWins;
	
	@FXML
	private Label playerWin;
	private int playerWins;
	
	@FXML
	private Label playerNumWins;
	
	@FXML
	private Label tempCompNum;
	private int tempComp;
	private int compCount;

	@Override
	public void start(Stage primaryStage) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("TwentyOneSceneBuilder.fxml"));
			Scene scene = new Scene(root,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Game of 21 by Tony Neese");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
		
	}
	
	
	
	@FXML
	public void start(ActionEvent start){
		Media media = new Media("file:///C:/Users/zachn_000/Desktop/CardShuffle.mp3");
	       MediaPlayer player = new MediaPlayer(media); 
	       player.play();
		
		tempCompNum.setText("");
		compCount = 0;
		playerTotal = 0;
		computerTotal = 0;
		showPlayerTotal.setText("");
		showComputerTotal.setText("?/21");
		compWin.setText("");
		playerWin.setText("");
		card1.setText("");
		card2.setText("");
		
		for(int i = 0; i < 11; i++){
			usedCards[i] = false;
		}
		
		dealComputer();
		dealPlayer();
		dealComputer();
		dealPlayer();
	}
	
	//HIT BUTTON
	@FXML
		public void hit(ActionEvent hit){
			
			if(playerTotal < 21){
				if(playerHits < 7)
				{
					dealPlayer();
				}
			}
			
			playerHits++;
			//update player total count
		}
		
		@FXML
		private void dealPlayer()
		   {
			      
			          int card = (int) (Math.random() * 11 + 1);
			      
			          if (usedCards[card-1] == true){
			        	  dealPlayer();
			          }
			          else if(usedCards[card-1] != true) {
			              usedCards[card-1] = true;
			              playerTotal = playerTotal + card;
			              
			              //String cardAsString = Integer.toString(card);
			              
			              card1.setText(card1.getText() + card + "        ");
			              
			              //add card to scene
			              //gp.add(addCard(cardAsString), playerCount, 5);
			              
			        	  
			          }
			          else
			          {
			        	  dealPlayer();
			          }
			          playerCurrentTotal = playerTotal;
			          showPlayerTotal.setText("");
			          showPlayerTotal.setText(showPlayerTotal.getText() + playerCurrentTotal + "/21");
			          if(playerCurrentTotal >= 21){
			        	  computerTurn();
			          }
			      
		   }
		
		
		//STAY BUTTON
		@FXML
		public void stay(ActionEvent stay){
			computerTurn();
		}
		
		@FXML
		private void computerTurn() {
			tempCompNum.setText(Integer.toString(tempComp));
			
			showComputerTotal.setText("");
			showComputerTotal.setText(showComputerTotal.getText() + computerCurrentTotal + "/21");
			while(computerCurrentTotal < 14)
			   {
				showComputerTotal.setText("");
				showComputerTotal.setText(showComputerTotal.getText() + computerCurrentTotal + "/21");
				   dealComputer();
			   }
			
			showComputerTotal.setText("");
			showComputerTotal.setText(showComputerTotal.getText() + computerCurrentTotal + "/21");
			
			   calculateWinner();
		}

		@FXML
		private void calculateWinner() {
			if(computerCurrentTotal > playerCurrentTotal && computerCurrentTotal <= 21){
				Media media = new Media("file:///C:/Users/zachn_000/Desktop/Booing.mp3");
			       MediaPlayer player = new MediaPlayer(media); 
			       player.play();
				   
				   compWin.setText("The computer has won.");
				   computerWins++;
				   String text = Integer.toString(computerWins);
				   //add computer wins text
				   computerNumWins.setText(text);
			   }
			   else if(playerCurrentTotal > computerCurrentTotal && playerCurrentTotal <=21)
			   {
				   Media media = new Media("file:///C:/Users/zachn_000/Desktop/Cheering.mp3");
			       MediaPlayer player = new MediaPlayer(media); 
			       player.play();
			       
				   playerWin.setText("You have won!");
				   playerWins++;
				   String text1 = Integer.toString(playerWins);
				   //add player wins text
				   playerNumWins.setText(text1);
			   }
			
			   else if(playerCurrentTotal > 21 && computerCurrentTotal <= 21)
			   {
				   Media media = new Media("file:///C:/Users/zachn_000/Desktop/Booing.mp3");
			       MediaPlayer player = new MediaPlayer(media); 
			       player.play();
			       
				   compWin.setText("The computer has won.");
				   computerWins++;
				   String text3 = Integer.toString(computerWins);
				   //add computer wins text
				   computerNumWins.setText(text3);
			   }
			
			   else if(computerCurrentTotal > 21 && playerCurrentTotal <= 21)
			   {
				   Media media = new Media("file:///C:/Users/zachn_000/Desktop/Cheering.mp3");
			       MediaPlayer player = new MediaPlayer(media); 
			       player.play();
			       
				   playerWin.setText("You have won!");
				   playerWins++;
				   String text2 = Integer.toString(playerWins);
				   //add player wins text
				   playerNumWins.setText(text2);
			   }
			   else
			   {
				   compWin.setText("It was a tie.");
				   playerWin.setText("It was a tie.");
			   }
			  
		}
		
		

		@FXML
		private void dealComputer() {
			int card = (int) (Math.random() * 11 + 1);
			
			if(compCount == 0){
				tempComp = card;
				compCount++;
			}
			
		
			if (usedCards[card-1] == true){
	        	  dealComputer();
	          }
			else if (usedCards[card-1] != true) {
	              usedCards[card-1] = true;
	              computerTotal = computerTotal + card;
	              
	              //String cardAsString = Integer.toString(card);
	              
	              card2.setText(card2.getText() + card + "        ");
	          }
	          else
	          {
	        	  dealComputer();
	          }
			
	          computerCurrentTotal = computerTotal;
			
	          //showComputerTotal.setText("");
	          //showComputerTotal.setText(showComputerTotal.getText() + computerCurrentTotal + "/21");
		}
}
