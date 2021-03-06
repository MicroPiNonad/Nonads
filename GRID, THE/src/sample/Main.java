/*
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 .-----------------. .----------------.  .-----------------. .----------------.  .----------------.  .----------------.
| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |
| | ____  _____  | || |     ____     | || | ____  _____  | || |      __      | || |  ________    | || |    _______   | |
| ||_   \|_   _| | || |   .'    `.   | || ||_   \|_   _| | || |     /  \     | || | |_   ___ `.  | || |   /  ___  |  | |
| |  |   \ | |   | || |  /  .--.  \  | || |  |   \ | |   | || |    / /\ \    | || |   | |   `. \ | || |  |  (__ \_|  | |
| |  | |\ \| |   | || |  | |    | |  | || |  | |\ \| |   | || |   / ____ \   | || |   | |    | | | || |   '.___`-.   | |
| | _| |_\   |_  | || |  \  `--'  /  | || | _| |_\   |_  | || | _/ /    \ \_ | || |  _| |___.' / | || |  |`\____) |  | |
| ||_____|\____| | || |   `.____.'   | || ||_____|\____| | || ||____|  |____|| || | |________.'  | || |  |_______.'  | |
| |              | || |              | || |              | || |              | || |              | || |              | |
| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |
'----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
*/
package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.*;

public class Main extends Application {

    public final int space = 42;
    public int turn = 0;
    public int winner = -1;
    public Text won = new Text();
    public Text lost = new Text();

    public Tile tile_set[][] = new Tile[space][space];
    public Tile sequence[][] = new Tile[space][space];

    String player1_target = new String();
    String player2_target = new String();

    public Tile player1_hand[][] = new Tile[3][3];
    public Tile player2_hand[][] = new Tile[3][3];


    public String binary_strings[] = {"001110100", "010101100", "001101100", "010011100", "000111010", "010101010",
                                      "010100101", "001100101", "100010101", "010010101", "101000101", "000111100",
                                      "010110100", "100001110", "010001110", "001001110", "110000110", "101000110",
                                      "011000110", "000110101", "000101110", "010100110", "001100110", "000011110",
                                      "100010110", "010010110", "001010110", "000100111", "000010111", "000001111",
                                      "100000111", "010000111", "001000111", "000110110"};


    //this draws the tiles onto the grid) = Integer.toString(2);
    public void handle(int x, int y, String player){
        int k = 0;
        for (int i = (y - 1); i <= (y + 1); i++){
            for (int j = (x - 1); j <= (x + 1); j++) {
                if (binary_strings[turn].charAt(k) == '1') {
                    tile_set[j][i].drawOne(Character.toString(binary_strings[turn].charAt(k)));
                }
                else {
                    tile_set[j][i].drawZero(Character.toString(binary_strings[turn].charAt(k)));
                }
                k++;
            }
        }

        turn++;
        draw_seq();
        targetCheck(x, y, player1_target, 0);
        targetCheck(x, y, player2_target, 1);

        if (winner == 0){
            stopGame();
            won.setVisible(true);
        }
        else if (winner == 1){
            stopGame();
            lost.setVisible(true);
        }
        if (turn == 34){
            System.out.println("DRAW");
        }

    }

    public void draw_hand(Tile argh[][], String player){
        int k = 0;
        for (int j = 0; j < 3; j++){
            for (int i = 0; i < 3; i++) {
                if (player.charAt(k) == '1') {
                    argh[i][j].border.setFill(Color.BLACK);
                }
                else {
                    argh[i][j].border.setFill(Color.LIGHTGRAY);
                }
                k++;
            }
        }
    }
    public void stopGame(){
        for (int i = 0; i < space; i++){
            for (int j = 0; j < space; j++){
                tile_set[i][j].play = false;
                tile_set[i][j].text.setText("0");
                tile_set[i][j].text.setFill(tile_set[i][j].border.getFill());
            }
        }
    }

    public void targetCheck(int x, int y, String target, int w){
        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 7; j++){
                if (((x - 3) + i) < 0 || ((y + 3) - j) < 0 || ((x - 3) + i) > space - 1 ||((y + 3) - j) > space - 1){
                    continue;
                }
                else if (tile_set[(x - 3) + i][(y + 3) - j].getText() == null){
                    continue;
                }
                else{
                    if (Character.toString(target.charAt(0)).equals(tile_set[(x - 3) + i][(y + 3) - j].getText())){
                        winCheck((x - 3) + i, (y + 3) - j, 0, w, target);
                        target = rotate_hand(target);
                        winCheck((x - 3) + i, (y + 3) - j, 1, w, target);
                        target = rotate_hand(target);
                        winCheck((x - 3) + i, (y + 3) - j, 2, w, target);
                        target = rotate_hand(target);
                        winCheck((x - 3) + i, (y + 3) - j, 3, w, target);
                        target = rotate_hand(target);
                    }
                    else{

                    }
                }
            }
        }
    }
    public void winCheck(int x, int y, int z, int w, String player){
        int k = 0;
        int counter = 0;
        for (int j = 0; j < 3; j++){
            for (int i = 0; i < 3; i++){
                if (i + x < 0 || j + y < 0 || i + x > space - 1 || j + y > space - 1){
                    continue;
                }
                else if (tile_set[i + x][j + y].getText().equals(Character.toString(player.charAt(k)))){
                    counter++;
                    if (counter == 9){
                        if (z == 0){
                            draw_win(x, y, w);
                        }
                        else if (z == 1){
                            draw_win(x, y, w);
                        }
                        else if (z == 2){
                            draw_win(x, y, w);
                        }
                        else if (z == 3){
                            draw_win(x, y, w);
                        }
                    }
                }
                else{
                    return;
                }
                k++;
            }
        }
    }
    public int winCheck_computer(int x, int y){
        int k = 0;
        int counter = 0;
        for (int j = 0; j < 3; j++){
            for (int i = 0; i < 3; i++){
                if (i + x < 0 || j + y < 0 || i + x > space - 1 || j + y > space - 1){
                    continue;
                }
                else if (tile_set[i + x][j + y].getText().equals(Character.toString(player2_target.charAt(k)))){
                    counter++;
                }
                else{
                    return counter;
                }
                k++;
            }
        }
        return counter;
    }

    public void draw_win(int x, int y, int player){
        winner = player;
        for (int j = 0; j < 3; j++){
            for (int i = 0; i < 3; i++){
                if (tile_set[x + i][y + j].text.getText().charAt(0) == '1'){

                    if (player == 0){
                        tile_set[x + i][y + j].text.setFill(Color.RED);
                        tile_set[x + i][y + j].border.setFill(Color.RED);
                    }
                    else{
                        tile_set[x + i][y + j].text.setFill(Color.GREEN);
                        tile_set[x + i][y + j].border.setFill(Color.GREEN);
                    }
                }
                else{
                    if (player == 0){
                        tile_set[x + i][y + j].text.setFill(Color.BLUE);
                        tile_set[x + i][y + j].border.setFill(Color.BLUE);
                    }
                    else{
                        tile_set[x + i][y + j].text.setFill(Color.YELLOW);
                        tile_set[x + i][y + j].border.setFill(Color.YELLOW);
                    }

                }
            }
        }

    }

    public void removeTile(int x, int y){
        for (int i = (y - 1); i <= (y + 1); i++){
            for (int j = (x - 1); j <= (x + 1); j++) {
                tile_set[j][i].text.setText("");
                tile_set[j][i].border.setFill(null);
            }
        }
    }

    public computerChoice computer_handle(int x, int y, int z){

        computerChoice choice = new computerChoice();
        choice.x = x;
        choice.y = y;
        choice.rotation = z;

        while (z > 0){
            binary_strings[turn] = rotate_hand(binary_strings[turn]);
            z--;
        }

        int k = 0;

        for (int i = (y - 1); i <= (y + 1); i++){
            for (int j = (x - 1); j <= (x + 1); j++) {
                if (binary_strings[turn].charAt(k) == '1') {
                    tile_set[j][i].drawOne(Character.toString(binary_strings[turn].charAt(k)));
                }
                else {
                    tile_set[j][i].drawZero(Character.toString(binary_strings[turn].charAt(k)));
                }
                k++;
            }
        }

        for (int i = 0; i < 5; i++){
            for (int j = 4; j >= 0; j--){
                if (((x - 3) + i) < 0 || (y + 1) - j < 0 || ((x - 3) + i) > space - 1 ||((y + 1) - j) > space - 1){
                    continue;
                }
                else if (tile_set[(x - 3) + i][(y + 1) - j].getText() == null){
                    continue;
                }
                    if (Character.toString(player2_target.charAt(0)).equals(tile_set[(x - 3) + i][(y + 1) - j].getText())){
                        int checker = winCheck_computer((x - 3) + i, (y + 1) - j);
                        if (choice.amount < checker){
                            choice.amount = checker;
                        }
                    }
                }

            }
        return choice;
    }
    public computerChoice computer_subHandle(int x, int y, int z){

        computerChoice sub_choice = new computerChoice();
        sub_choice.x = x;
        sub_choice.y = y;
        sub_choice.rotation = z;

        while (z > 0){
            binary_strings[turn + 2] = rotate_hand(binary_strings[turn + 2]);
            z--;
        }

        int k = 0;

        for (int i = (y - 1); i <= (y + 1); i++){
            for (int j = (x - 1); j <= (x + 1); j++) {
                if (binary_strings[turn + 2].charAt(k) == '1') {
                    tile_set[j][i].drawOne(Character.toString(binary_strings[turn + 2].charAt(k)));
                }
                else {
                    tile_set[j][i].drawZero(Character.toString(binary_strings[turn + 2].charAt(k)));
                }
                k++;
            }
        }

        for (int i = 0; i < 5; i++){
            for (int j = 4; j >= 0; j--){
                if (((x - 3) + i) < 0 || (y + 1) - j < 0 || ((x - 3) + i) > space - 1 ||((y + 1) - j) > space - 1){
                    continue;
                }
                else if (tile_set[(x - 3) + i][(y + 1) - j].getText() == null){
                    continue;
                }
                if (Character.toString(player2_target.charAt(0)).equals(tile_set[(x - 3) + i][(y + 1) - j].getText())){
                    int checker = winCheck_computer((x - 3) + i, (y + 1) - j);
                    if (sub_choice.amount < checker){
                        sub_choice.amount = checker;
                    }
                }
            }
        }
        return sub_choice;

    }
    public int sub_handle(int x, int y) {

        ArrayList<computerChoice> sub_look = new ArrayList<>();
        String hold = binary_strings[turn + 2];

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (((x - 3) + i) < 0 || ((y + 3) - j) < 0 || ((x - 3) + i) > space - 1 || ((y + 3) - j) > space - 1) {
                    continue;
                } else if (tile_set[(x - 3) + i][(y + 3) - j].getText() == null) {
                    continue;
                }
                if (placeAble((x - 3) + i, (y + 3) - j)) {
                    sub_look.add(computer_subHandle((x - 3) + i, (y + 3) - j, 0));
                    removeTile((x - 3) + i, (y + 3) - j);
                    binary_strings[turn + 2] = hold;

                    sub_look.add(computer_subHandle((x - 3) + i, (y + 3) - j, 1));
                    removeTile((x - 3) + i, (y + 3) - j);
                    binary_strings[turn + 2] = hold;

                    sub_look.add(computer_subHandle((x - 3) + i, (y + 3) - j, 2));
                    removeTile((x - 3) + i, (y + 3) - j);
                    binary_strings[turn + 2] = hold;

                    sub_look.add(computer_subHandle((x - 3) + i, (y + 3) - j, 3));
                    removeTile((x - 3) + i, (y + 3) - j);
                    binary_strings[turn + 2] = hold;

                }

            }
        }
        int amount = 0;
        for (int i = 0; i < sub_look.size(); i++){
            if (sub_look.get(i).amount > amount){
                amount = sub_look.get(i).amount;
            }
        }

        return amount;
    }

    public void play(){

        ArrayList<computerChoice> look = new ArrayList<>();

        int indx = 0;
        int amount = 0;
        String hold = binary_strings[turn];
        for (int i = 0; i < space; i++){
            for (int j = 0; j < space; j++){
                if (placeAble(j, i)){

                    look.add(computer_handle(j, i, 0));
                    if (look.get(indx).amount == 9){
                        handle(look.get(indx).x, look.get(indx).y, player2_target);
                        return;
                    }
                    amount = sub_handle(j, i);
                    look.get(indx).amount =+ amount;
                    removeTile(j, i);
                    binary_strings[turn] = hold;
                    indx++;

                    look.add(computer_handle(j, i, 1));
                    if (look.get(indx).amount == 9){
                        handle(look.get(indx).x, look.get(indx).y, player2_target);
                        return;
                    }
                    amount = sub_handle(j, i);
                    look.get(indx).amount =+ amount;
                    removeTile(j, i);
                    binary_strings[turn] = hold;
                    indx++;

                    look.add(computer_handle(j, i, 2));
                    if (look.get(indx).amount == 9){
                        handle(look.get(indx).x, look.get(indx).y, player2_target);
                        return;
                    }
                    amount = sub_handle(j, i);
                    look.get(indx).amount =+ amount;
                    removeTile(j, i);
                    binary_strings[turn] = hold;
                    indx++;

                    look.add(computer_handle(j, i, 3));
                    if (look.get(indx).amount == 9){
                        handle(look.get(indx).x, look.get(indx).y, player2_target);
                        return;
                    }
                    amount = sub_handle(j, i);
                    look.get(indx).amount =+ amount;
                    removeTile(j, i);
                    binary_strings[turn] = hold;
                    indx++;
                }
            }
        }
        int xx = 0;
        int yy = 0;
        int zz = 0;
        int amountt = 0;

        for (int i = 0; i < look.size(); i++){
            if (look.get(i).amount > amountt){
                xx = look.get(i).x;
                yy = look.get(i).y;
                zz = look.get(i).rotation;
                amountt = look.get(i).amount;
            }
        }

        while (zz > 0){
            binary_strings[turn] = rotate_hand(binary_strings[turn]);
            zz--;
        }

        handle(xx, yy, player2_target);
    }
    public void draw_seq() {
        int k = 0;

        for (int i = 0; i < 18; i++){
            for (int j = 0; j < 3; j++){
                sequence[i][j].border.setFill(null);
            }
        }

        do {
            int kk = 0;
            if (turn + k > binary_strings.length - 1) {
                break;
            }
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 3; i++) {
                    if (binary_strings[turn + k].charAt(kk) == '1') {
                        sequence[i + (k * 3)][j].border.setFill(Color.BLACK);
                    } else {
                        sequence[i + (k * 3)][j].border.setFill(Color.LIGHTGRAY);
                    }
                    kk++;
                }
            }
            k++;
        } while (k < 6);

    }

    //checks if tile is placeable
    public boolean placeAble(int x, int y){

        int amount = 0;

        if (y == 0 || y == (space - 1) || x == 0 || x == (space - 1)){
            return false;
        }

        for (int i = 0; i <= 4; i++){
            if ((x - 2) + i < 0 || ((x - 2) + i) > (space - 1) || (y + 2) > (space - 1) || (y + 2) < 0){
                continue;
            }
            else if (!this.tile_set[(x - 2) + i][y + 2].getText().isEmpty()){
                amount++;
            }
        }

        for (int i = 0; i <= 4; i++){
            if ((x - 2) + i < 0 || ((x - 2) + i) > (space - 1) || (y - 2) > (space - 1) || (y - 2) < 0){
                continue;
            }
           else if (!this.tile_set[(x - 2) + i][y - 2].getText().isEmpty()){
                amount++;
            }
        }

        for (int j = 0; j < 3; j++){
            for (int i = 0; i < 2; i++){
                if ((x - 2) + (i * 4) < 0 || (x - 2) + (i * 4) > (space - 1) || (y - 1) + j < 0 || (y - 1) + j > (space - 1)){
                    continue;
                }
                if (!this.tile_set[(x - 2) + (i * 4)][(y - 1) + j].getText().isEmpty()){
                    amount++;
                }
            }
        }

        if (amount <= 1){
            return false;
        }

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (!this.tile_set[(x - 1) + i][(y - 1) + j].getText().isEmpty()){
                    return false;
                }
            }
        }

        return true;
    }

    public Parent content(boolean type, int width, int height) {
        Pane root = new Pane();
        root.setPrefSize(width, height);

        for (int i = 0; i < space; i++) {
            for (int j = 0; j < space; j++) {
                Tile game_tile = new Tile(i, j, true);

                game_tile.setTranslateX((i + 25) * 20);
                game_tile.setTranslateY(j * 20);

                tile_set[i][j] = game_tile;
                root.getChildren().addAll(game_tile);
            }
        }
        int k = 0;
        for (int i = 0; i < 18; i++) {
            if (i % 3 == 0) {
                k++;
            }
            for (int j = 0; j < 3; j++) {
                Tile sequence_tile = new Tile(0, 0, false);
                sequence_tile.setTranslateX((i + k) * 20);
                sequence_tile.setTranslateY((j + 1) * 20);
                sequence[i][j] = sequence_tile;
                root.getChildren().add(sequence_tile);
            }
        }

        Button deck_rotate = new Button("Rotate Next Tile");
        deck_rotate.setPrefSize(130, 20);
        deck_rotate.setTranslateX(10);
        deck_rotate.setTranslateY(110);

        Button player1_rotate = new Button("Rotate Player 1's T Piece");
        player1_rotate.setPrefSize(200, 20);
        player1_rotate.setTranslateX(10);
        player1_rotate.setTranslateY(300);

        Button player2_rotate = new Button("Rotate Computer's T Piece");
        player2_rotate.setPrefSize(200, 20);
        player2_rotate.setTranslateX(10);
        player2_rotate.setTranslateY(370);

        lost = new Text();
        lost.setText("YOU LOST! HAHAHA!");
        lost.setVisible(false);
        lost.setTranslateX(150);
        lost.setTranslateY(470);

        won = new Text();
        won.setText("YOU WON!");
        won.setVisible(false);
        won.setTranslateX(150);
        won.setTranslateY(470);


        Button exit = new Button("EXIT GAME?");
        exit.setVisible(true);
        exit.setPrefSize(130, 20);
        exit.setTranslateX(150);
        exit.setTranslateY(570);


        player1_rotate.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                player1_target = rotate_hand(player1_target);
                draw_hand(player1_hand, player1_target);
            }
        });

        player2_rotate.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                player2_target = rotate_hand(player2_target);
                draw_hand(player2_hand, player2_target);
            }
        });

        deck_rotate.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                binary_strings[turn] = rotate_hand(binary_strings[turn]);
                draw_hand(sequence, binary_strings[turn]);
            }
        });

        exit.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                System.exit(0);
            }
        });
        Text player1 = new Text("Player 1");
        player1.setTranslateX(10);
        player1.setTranslateY(275);

        Text player2 = new Text("Player 2");
        player2.setTranslateX(120);
        player2.setTranslateY(275);

        player1_target = pick_tile();
        player2_target = pick_tile();

        k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile one_hand = new Tile(0, 0, false);
                Tile two_hand = new Tile(0, 0, false);

                one_hand.setTranslateX((i * 20) + 10);
                one_hand.setTranslateY((j * 20) + 200);

                two_hand.setTranslateX((i * 20) + 120);
                two_hand.setTranslateY((j * 20) + 200);

                player1_hand[i][j] = one_hand;
                player2_hand[i][j] = two_hand;

                root.getChildren().addAll(one_hand, two_hand);
                k++;
            }
        }
        root.getChildren().addAll(won, lost, exit, deck_rotate, player1_rotate, player2_rotate, player1, player2);
        return root;
    }

    @Override
    public void start(Stage the_grid) throws Exception{

        shuffle_sequence();

        the_grid.setResizable(false);

        the_grid.setScene(new Scene(content(false, 1340, 840)));

        draw_hand(player1_hand, player1_target);
        draw_hand(player2_hand, player2_target);

        handle(space/2, space/2, player2_target);

        draw_seq();
        the_grid.show();
    }

    public String rotate_hand(String player){

        StringBuilder temp = new StringBuilder("123456789");
        temp.setCharAt(6, player.charAt(0));
        temp.setCharAt(3, player.charAt(1));
        temp.setCharAt(0, player.charAt(2));
        temp.setCharAt(7, player.charAt(3));
        temp.setCharAt(1, player.charAt(5));
        temp.setCharAt(4, player.charAt(4));
        temp.setCharAt(8, player.charAt(6));
        temp.setCharAt(5, player.charAt(7));
        temp.setCharAt(2, player.charAt(8));

        return temp.toString();
    }

    public String pick_tile(){

        String hand = binary_strings[turn];
        turn++;
        return hand;
    }

    public void shuffle_sequence(){
        Random rd = new Random();
        int index;
        String temp;
        for (int i = binary_strings.length - 1; i > 0; i--){
            index = rd.nextInt(i + 1);
            temp = binary_strings[index];
            binary_strings[index] = binary_strings[i];
            binary_strings[i] = temp;
        }
    }

    public class computerChoice {
        public int x;
        public int y;
        public int amount;
        public int rotation;

        public computerChoice(){
            x = 0;
            y = 0;
            amount = 0;
            rotation = 0;
        }
    }

    private  class Tile extends StackPane{
        private Text text = new Text();
        public Rectangle border;
        public boolean play;

        public Tile(int i, int j, boolean type) {
            border = new Rectangle(19,19);
            play = type;
            border.setFill(null);
            border.setStroke(Color.BLACK);
            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

            if (play == true) {
                setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        if (placeAble(i, j)) {
                            handle(i, j, player1_target);
                            play();
                        }
                    }
                });
            }
            else{

            }
        }

        public String getText(){
            return text.getText();
        }

        private void drawOne(String i){
            border.setFill(Color.BLACK);
            text.setText(i);
            text.setFill(Color.BLACK);
        }

        private void drawZero(String i){
            border.setFill(Color.LIGHTGRAY);
            text.setText(i);
            text.setFill(Color.LIGHTGRAY);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
