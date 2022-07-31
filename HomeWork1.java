package lesson4;
import java.util.Random;
import java.util.Scanner;
public class HomeWork1 {
    private static final int SIZE = 3;
    private static final char DOT_EMPTY = '•';
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    public static final String HEADER_FIRST_SYMBOL = "♥";
    public static final String SPACE_MAP = " ";
    private static char[][] MAP = new char[SIZE][SIZE];
    private static Scanner in = new Scanner(System.in);
    private static Random random = new Random();
    private static int turnsCount;
    private static int lastTurnRow;
    private static int lastTurnColumn;
    public static void start() {
        do {
            System.out.println("\n\nИгра начинается!!!");
            init();
            printMap();
            playGame();
        } while (isContinueGame());
        endGame();
    }private static void init() {
        turnsCount = 0;
        initMap();
    }private static void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                MAP[i][j] = DOT_EMPTY;}}}
    private static void printMap() {
        printMapHeader();
        printMapBody();}

    private static void printMapHeader() {
        System.out.print(HEADER_FIRST_SYMBOL + SPACE_MAP);
        for (int i = 0; i < SIZE; i++) {
            printMapNumber(i);}
        System.out.println();}
    private static void printMapNumber(int i) {
        System.out.print(i + 1 + SPACE_MAP);
    }
    private static void printMapBody() {
        for (int i = 0; i < SIZE; i++) {
            printMapNumber(i);
            for (int j = 0; j < SIZE; j++) {
                System.out.print(MAP[i][j] + SPACE_MAP);}
            System.out.println();}}

    private static void playGame() {
        while (true) {
            humanTurn();
            printMap();
            if (checkEnd(DOT_HUMAN)) {
                break;}
            aiTurn();
            printMap();
            if (checkEnd(DOT_AI)) {
                break;}}}

    private static void humanTurn() {
        System.out.println("\nХОД ЧЕЛОВЕКА!");

        int rowNumber;
        int columnNumber;

        while (true) {
            System.out.print("Введите номер строки: ");
            rowNumber = getValidNumberFromScanner() - 1;

            System.out.print("Введите номер столбца: ");
            columnNumber = getValidNumberFromScanner()  - 1;

            if (isCellFree(rowNumber, columnNumber)) {
                break;}
            System.out.printf("ОШИБКА! Ячейка с координатами %s:%s уже используется%n%n", rowNumber + 1,
                    columnNumber + 1);}

        MAP[rowNumber][columnNumber] = DOT_HUMAN;
        turnsCount++;}
    private static int getValidNumberFromScanner() {
        while (true) {
            if (in.hasNextInt()) {
                int n = in.nextInt();
                if (isNumberValid(n)) {
                    return n;}
                System.out.println("ОШБИКА! Проверьте значение координаты. Допускается от 1 до " + SIZE);
            } else {System.out.println("ОШИБКА! Ввод допускает лишь целые числа!");
                in.next();}}}
    private static boolean isCellFree(int rowNumber, int columnNumber) {
        return MAP[rowNumber][columnNumber] == DOT_EMPTY;}
    private static boolean isNumberValid(int n) {
        return n >= 1 && n <= SIZE;
    }
    private static boolean checkEnd(char symbol) {
        if (checkWin(symbol)) {
            if (symbol == DOT_HUMAN) {
                System.out.println("УРА ВЫ ПОБЕДИЛИ!");
            } else {System.out.println("Компьютер победил");}
            return true;}
        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;}
        return false;}
    private static boolean checkWin(char symbol) {
        if (MAP[0][0] == symbol && MAP[0][1] == symbol && MAP[0][2] == symbol) {
            return true;}
        if (MAP[1][0] == symbol && MAP[1][1] == symbol && MAP[1][2] == symbol) {
            return true;}
        if (MAP[2][0] == symbol && MAP[2][1] == symbol && MAP[2][2] == symbol) {
            return true;}
        if (MAP[0][0] == symbol && MAP[1][0] == symbol && MAP[2][0] == symbol) {
            return true;}
        if (MAP[0][1] == symbol && MAP[1][1] == symbol && MAP[2][1] == symbol) {
            return true;}
        if (MAP[0][2] == symbol && MAP[1][2] == symbol && MAP[2][2] == symbol) {
            return true;}
        if (MAP[0][0] == symbol && MAP[1][1] == symbol && MAP[2][2] == symbol) {
            return true;}
        if (MAP[0][2] == symbol && MAP[1][1] == symbol && MAP[2][0] == symbol) {
            return true;}
        return false;}
    private static boolean checkDraw() {
        return turnsCount >= SIZE * SIZE;}
    private static void aiTurn() {
        System.out.println("\nХОД КОМПУКТЕРА!");
        int rowNumber;
        int columnNumber;
        do {rowNumber = random.nextInt(SIZE);
            columnNumber = random.nextInt(SIZE);

        } while (!isCellFree(rowNumber, columnNumber));
        MAP[rowNumber][columnNumber] = DOT_AI;
        turnsCount++;}
    private static boolean isContinueGame() {
        System.out.println("Хотите продолжить? y\\n");
        return switch (in.next()){
            case "y", "yes", "д", "да", "+" -> true;
            default -> false;};}
    private static void endGame() {
        in.close();
        System.out.println("Возвращайся!!!");}}
