
import java.io.*;
import java.util.*;

public class Lexical_Analyzer_for_java {

    static char lookahead;

    public static void main(String[] args) throws IOException {
        // Write commands here to open “input.txt” file for reading and “output.txt”
        // file for writing

        FileReader input = new FileReader("input.txt");
        BufferedReader read = new BufferedReader(input);
        
       
        System.out.println("Lexemes                                               Tokens");
        tokenizer(read);

    }

    //
    public static void tokenizer(BufferedReader read) throws IOException {

        // Write statement(s) here to store all reserved words of C/Java into an array
        String[] ReservedWords = {
            "abstract", "continue", "for", "new", "switch",
            "assert", "default", "if", "package", "synchronized",
            "boolean", "do", "goto", "private", "this",
            "break", "double", "implements", "protected", "throw",
            "byte", "else", "import", "public", "throws",
            "case", "enum", "instanceof", "return", "transient",
            "catch", "extends", "int", "short", "try",
            "char", "final", "interface", "static", "void",
            "class", "finally", "long", "strictfp", "volatile",
            "const", "float", "native", "super", "while",
            "null", "true", "false"};

        // Create an array named “lexeme” to store lexeme. char lexeme[30];
        char lexeme[] = new char[1000];

        int flag;
        int i = 0;
        int j = 0;
        int state = 0;

        lookahead = (char) read.read();
        while (lookahead != '\uffff') {

            switch (state) {

                case 0://done + checked 

                    if (lookahead == '\r' || lookahead == '\t' || lookahead == ' '
                            || lookahead == '\n' || lookahead == '\r') {
                        state = 0;
                        lookahead = (char) read.read();
                    } else if (Character.isLetter(lookahead) || lookahead == '_' || lookahead == '$') {
                        state = 1;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '+') {
                        state = 3;
                        lexeme[i++] = lookahead;
                    } else if (Character.isDigit(lookahead)) {
                        state = 7;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '-') {
                        state = 13;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '*') {
                        state = 17;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '%') {
                        state = 20;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '=') {
                        state = 23;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '>') {
                        state = 26;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '<') {
                        state = 30;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '!') {
                        state = 34;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '&') {
                        state = 37;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '|') {
                        state = 40;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '~') {
                        state = 43;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '^') {
                        state = 44;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '/') {
                        state = 45;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '(') {
                        state = 53;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == ')') {
                        state = 54;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '{') {
                        state = 55;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '}') {
                        state = 56;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == ':') {
                        state = 57;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '\'') { //single_qutation
                        read.mark(900);
                        state = 58;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '"') {
                        read.mark(900);
                        state = 63;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == ',') {
                        state = 68;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '.') {
                        state = 69;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == ']') {
                        state = 72;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '?') {
                        state = 73;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '[') {
                        state = 74;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == ';') {
                        state = 75;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '\\') {
                        state = 76;
                        lexeme[i++] = lookahead;
                    } else {

                        state = 100;
                        lexeme[i++] = lookahead;
                    }
                    break;

                case 1://done + checked 
                    lookahead = (char) read.read();
                    if (Character.isLetter(lookahead) || Character.isDigit(lookahead) || lookahead == '_' || lookahead == '$') {
                        state = 1;
                        lexeme[i++] = lookahead;

                    } else {
                        state = 2;
                        lexeme[i] = '\0';                         // Storing null character at the end
                        flag = checkReserveWord(ReservedWords, lexeme, i);    //chek with reserve words
                        print(lexeme, "ID", flag, i);
                        i = 0;
                    }
                    break;
                case 2://done + checked  
                    state = 0;
                    break;
                case 3://done + checked  
                    lookahead = (char) read.read();
                    if (lookahead == '+') {
                        state = 4;
                        lexeme[i++] = lookahead;
                        lexeme[i] = '\0';                         // Storing null character at the end
                        print(lexeme, "inc-op", 0, i);
                        i = 0;
                    } else if (lookahead == '=') {
                        state = 5;
                        lexeme[i++] = lookahead;
                        lexeme[i] = '\0';                         // Storing null character at the end
                        print(lexeme, "plus-equal", 0, i);
                        i = 0;
                    } else {
                        state = 6;
                        lexeme[i] = '\0';                         // Storing null character at the end
                        print(lexeme, "Arithmetic-plus", 0, i);
                        i = 0;
                    }
                    break;

                case 4://done + checked  
                    lookahead = (char) read.read();
                    state = 0;

                    break;
                case 5://done + checked  
                    lookahead = (char) read.read();
                    state = 0;

                    break;
                case 6://done + checked  
                    state = 0;
                    if (lookahead == '\r' || lookahead == '\t' || lookahead == ' '
                            || lookahead == '\n' || lookahead == '\r') {
                        lookahead = (char) read.read();
                    }

                    break;
                case 7://done + checked   
                    lookahead = (char) read.read();
                    if (Character.isDigit(lookahead)) {
                        state = 7;
                        lexeme[i++] = lookahead;
                    } else if (lookahead == '.') {
                        state = 8;
                        lexeme[i++] = lookahead;
                    } else {
                        state = 12;
                        lexeme[i] = '\0';                         // Storing null character at the end
                        print(lexeme, "int-literal", 0, i);
                        i = 0;
                    }
                    break;

                case 8://done + checked     
                    lookahead = (char) read.read();
                    if (Character.isDigit(lookahead)) {
                        state = 10;
                        lexeme[i++] = lookahead;
                    } else {
                        state = 9;
                        lexeme[i] = '\0';                         // Storing null character at the end
                        print(lexeme, "double-literal", 0, i);
                        i = 0;
                    }
                    break;
                case 9://done + checked  
                    state = 0;
                    break;
                case 10://done + checked   
                    lookahead = (char) read.read();
                    if (Character.isDigit(lookahead)) {
                        state = 10;
                        lexeme[i++] = lookahead;
                    } else {
                        state = 11;
                        lexeme[i] = '\0';                         // Storing null character at the end
                        print(lexeme, "double-literal", 0, i);
                        i = 0;
                    }
                    break;
                case 11://done + checked  
                    state = 0;
                    break;
                case 12://done + checked    
                    state = 0;
                    break;
                case 13://done + checked   
                    lookahead = (char) read.read();
                    if (lookahead == '-') {
                        state = 14;
                        lexeme[i++] = lookahead;
                        lexeme[i] = '\0';                         // Storing null character at the end
                        print(lexeme, "decrement-operato", 0, i);
                        i = 0;
                    } else if (lookahead == '=') {
                        state = 15;
                        lexeme[i++] = lookahead;
                        lexeme[i] = '\0';                         // Storing null character at the end
                        print(lexeme, "minus-equal", 0, i);
                        i = 0;
                    } else {
                        state = 16;
                        lexeme[i] = '\0';
                        print(lexeme, "Arithmetic-minus", 0, i);
                        i = 0;
                    }
                    break;
                case 14://done + checked   
                    lookahead = (char) read.read();
                    state = 0;
                    break;
                case 15://done + checked   
                    lookahead = (char) read.read();
                    state = 0;
                    //read.unread(lookahead);
                    break;
                case 16://done + checked   
                    state = 0;
                    if (lookahead == '\r' || lookahead == '\t' || lookahead == ' '
                            || lookahead == '\n' || lookahead == '\r') {
                        lookahead = (char) read.read();
                    }
                    break;
                case 17://done + checked
                    lookahead = (char) read.read();
                    if (lookahead == '=') {
                        state = 18;
                        lexeme[i++] = lookahead;
                        lexeme[i] = '\0';                         // Storing null character at the end
                        print(lexeme, "Multi-equal", 0, i);
                        i = 0;
                    } else {
                        state = 19;
                        lexeme[i] = '\0';
                        print(lexeme, "Arithmetic-Multiplication", 0, i);
                        i = 0;
                    }
                    break;
                case 18://done + checked
                    lookahead = (char) read.read();
                    state = 0;
                    break;
                case 19://done + checked
                    state = 0;
                    if (lookahead == '\r' || lookahead == '\t' || lookahead == ' '
                            || lookahead == '\n' || lookahead == '\r') {
                        lookahead = (char) read.read();
                    }
                    break;
                case 20://done + checked
                    lookahead = (char) read.read();
                    if (lookahead == '=') {
                        state = 21;
                        lexeme[i++] = lookahead;
                        lexeme[i] = '\0';                         // Storing null character at the end
                        print(lexeme, "Modulo-equal", 0, i);
                        i = 0;
                    } else {
                        state = 22;
                        lexeme[i] = '\0';
                        print(lexeme, "Arithmetic-Modulo", 0, i);
                        i = 0;
                    }
                    break;
                case 21://done + checked
                    lookahead = (char) read.read();
                    state = 0;
                    break;
                case 22://done + checked
                    state = 0;
                    if (lookahead == '\r' || lookahead == '\t' || lookahead == ' '
                            || lookahead == '\n' || lookahead == '\r') {
                        lookahead = (char) read.read();
                    }
                    break;
                case 23://done + checked
                    lookahead = (char) read.read();
                    if (lookahead == '=') {
                        state = 24;
                        lexeme[i++] = lookahead;
                        lexeme[i] = '\0';                         // Storing null character at the end
                        print(lexeme, "Relational-equal", 0, i);
                        i = 0;
                    } else {
                        state = 25;
                        lexeme[i] = '\0';
                        print(lexeme, "Assignment-operator", 0, i);
                        i = 0;
                    }
                    break;
                case 24://done + checked
                    lookahead = (char) read.read();
                    state = 0;
                    break;
                case 25://done + checked
                    state = 0;
                    if (lookahead == '\r' || lookahead == '\t' || lookahead == ' '
                            || lookahead == '\n' || lookahead == '\r') {
                        lookahead = (char) read.read();
                    }
                    break;
                case 26://done + checked
                    lookahead = (char) read.read();
                    if (lookahead == '>') {
                        state = 27;
                        lexeme[i++] = lookahead;
                        lexeme[i] = '\0';                         // Storing null character at the end
                        print(lexeme, "Right-shift", 0, i);
                        i = 0;
                    } else if (lookahead == '=') {
                        state = 28;
                        lexeme[i++] = lookahead;
                        lexeme[i] = '\0';                         // Storing null character at the end
                        print(lexeme, "Relational-GE", 0, i);
                        i = 0;
                    } else {
                        state = 29;
                        lexeme[i] = '\0';
                        print(lexeme, "Relational-GT", 0, i);
                        i = 0;
                    }
                    break;
                case 27://done + checked
                    lookahead = (char) read.read();
                    state = 0;
                    break;
                case 28://done + checked
                    lookahead = (char) read.read();
                    state = 0;
                    break;
                case 29://done + checked
                    state = 0;
                    if (lookahead == '\r' || lookahead == '\t' || lookahead == ' '
                            || lookahead == '\n' || lookahead == '\r') {
                        lookahead = (char) read.read();
                    }
                    break;
                case 30://done + checked
                    lookahead = (char) read.read();
                    if (lookahead == '<') {
                        state = 31;
                        lexeme[i++] = lookahead;
                        lexeme[i] = '\0';                         // Storing null character at the end
                        print(lexeme, "Left-shift", 0, i);
                        i = 0;
                    } else if (lookahead == '=') {
                        state = 32;
                        lexeme[i++] = lookahead;
                        lexeme[i] = '\0';                         // Storing null character at the end
                        print(lexeme, "Relational-LE", 0, i);
                        i = 0;
                    } else {
                        state = 33;
                        lexeme[i] = '\0';
                        print(lexeme, "Relational-LT", 0, i);
                        i = 0;
                    }
                    break;
                case 31://done + checked
                    lookahead = (char) read.read();
                    state = 0;
                    break;
                case 32://done + checked
                    lookahead = (char) read.read();
                    state = 0;
                    break;
                case 33://done + checked
                    state = 0;
                    if (lookahead == '\r' || lookahead == '\t' || lookahead == ' '
                            || lookahead == '\n' || lookahead == '\r') {
                        lookahead = (char) read.read();
                    }
                    break;
                case 34://done + checked
                    lookahead = (char) read.read();
                    if (lookahead == '=') {
                        state = 36;
                        lexeme[i++] = lookahead;
                        lexeme[i] = '\0';                         // Storing null character at the end
                        print(lexeme, "Relational-NotEqual", 0, i);
                        i = 0;
                    } else {
                        state = 35;
                        lexeme[i] = '\0';
                        print(lexeme, "Logical-Not", 0, i);
                        i = 0;
                    }
                    break;
                case 35://done + checked
                    state = 0;
                    if (lookahead == '\r' || lookahead == '\t' || lookahead == ' '
                            || lookahead == '\n' || lookahead == '\r') {
                        lookahead = (char) read.read();
                    }
                    break;
                case 36://done + checked
                    lookahead = (char) read.read();
                    state = 0;
                    break;
                case 37://done + checked
                    lookahead = (char) read.read();
                    if (lookahead == '&') {
                        state = 38;
                        lexeme[i++] = lookahead;
                        lexeme[i] = '\0';                         // Storing null character at the end
                        print(lexeme, "Logcal-AND", 0, i);
                        i = 0;
                    } else {
                        state = 39;
                        lexeme[i] = '\0';
                        print(lexeme, "Bitwise-AND", 0, i);
                        i = 0;
                    }
                    break;
                case 38://done + checked
                    lookahead = (char) read.read();
                    state = 0;
                    break;
                case 39://done + checked
                    state = 0;
                    if (lookahead == '\r' || lookahead == '\t' || lookahead == ' '
                            || lookahead == '\n' || lookahead == '\r') {
                        lookahead = (char) read.read();
                    }
                    break;
                case 40://done + checked
                    lookahead = (char) read.read();
                    if (lookahead == '|') {
                        state = 42;
                        lexeme[i++] = lookahead;
                        lexeme[i] = '\0';                         // Storing null character at the end
                        print(lexeme, "Logical-OR", 0, i);
                        i = 0;
                    } else {
                        state = 41;
                        lexeme[i] = '\0';
                        print(lexeme, "Bitwise-OR", 0, i);
                        i = 0;
                    }
                    break;
                case 41://done + checked
                    state = 0;
                    if (lookahead == '\r' || lookahead == '\t' || lookahead == ' '
                            || lookahead == '\n' || lookahead == '\r') {
                        lookahead = (char) read.read();
                    }
                    break;
                case 42://done + checked
                    lookahead = (char) read.read();
                    state = 0;
                    break;
                case 43://done + checked
                    lookahead = (char) read.read();
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "Bitwise-Complement", 0, i);
                    i = 0;
                    break;
                case 44://done + checked
                    lookahead = (char) read.read();
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "Logical-XOR", 0, i);
                    i = 0;
                    break;
                case 45://done + checked
                    lookahead = (char) read.read();
                    if (lookahead == '/') {
                        state = 46;
                        lexeme[i++] = lookahead;

                    } else if (lookahead == '*') {
                        state = 48;
                        lexeme[i++] = lookahead;

                    } else if (lookahead == '=') {
                        state = 51;
                        lexeme[i++] = lookahead;
                        lexeme[i] = '\0';                         // Storing null character at the end
                        print(lexeme, "Divison-equal", 0, i);
                        i = 0;
                    } else {
                        state = 52;
                        lexeme[i] = '\0';
                        print(lexeme, "Arithmetic-Divison", 0, i);
                        i = 0;
                    }
                    break;
                case 46://done + checked
                    while (lookahead != '\n' && lookahead != '\r' && lookahead != '\uffff') {
                        lookahead = (char) read.read();
                        lexeme[i++] = lookahead;
                    }
                    lexeme[i - 1] = '\0';                         // Storing null character at the end
                    print(lexeme, "Single-Line-Comment", 0, i);
                    i = 0;
                    if (lookahead == '\r' || lookahead == '\t' || lookahead == ' '
                            || lookahead == '\n' || lookahead == '\r') {
                        lookahead = (char) read.read();
                    }
                    state = 0;
                    break;
                case 48://done + checked
                    lookahead = (char) read.read();
                    lexeme[i++] = lookahead;
                    while (lookahead != '*' && lookahead != '\uffff') {

                        lookahead = (char) read.read();
                        lexeme[i++] = lookahead;

                    }
                    if (lookahead == '*') {
                        state = 49;
                    }

                    break;
                case 49://done + checked
                    lookahead = (char) read.read();
                    lexeme[i++] = lookahead;
                    if (lookahead == '/') {
                        state = 50;
                    } else if (lookahead == '*') {
                        state = 49;
                    } else {
                        state = 48;
                    }
                    break;
                case 50://done + checked
                    lookahead = (char) read.read();
                    lexeme[i++] = lookahead;
                    lexeme[i - 1] = '\0';                         // Storing null character at the end
                    print(lexeme, "Multi-Lines-Comment", 0, i);
                    i = 0;
                    state = 0;
                    break;
                case 51://done + checked
                    lookahead = (char) read.read();
                    state = 0;
                    break;
                case 52://done + checked
                    state = 0;
                    if (lookahead == '\r' || lookahead == '\t' || lookahead == ' '
                            || lookahead == '\n' || lookahead == '\r') {
                        lookahead = (char) read.read();
                    }
                    break;
                case 53://done + checked
                    lookahead = (char) read.read();
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "Left-Paren", 0, i);
                    i = 0;
                    break;
                case 54://done + checked
                    lookahead = (char) read.read();
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "Right-Paren", 0, i);
                    i = 0;
                    break;
                case 55://done + checked 
                    lookahead = (char) read.read();
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "Left-Curly", 0, i);
                    i = 0;
                    break;
                case 56://done + checked 
                    lookahead = (char) read.read();
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "Right-Curly", 0, i);
                    i = 0;
                    break;
                case 57://done + checked 
                    lookahead = (char) read.read();
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "Colon", 0, i);
                    i = 0;
                    break;

                case 58://done + checked 
                    lookahead = (char) read.read();
                    if (lookahead == '\\') {
                        lexeme[i++] = lookahead;
                        lookahead = (char) read.read();
                        if (lookahead == 't' || lookahead == 'n' || lookahead == 'b' || lookahead == 'r' || lookahead == 'f' || lookahead == '\\' || lookahead == '\'') {
                            state = 58;
                            lexeme[i++] = lookahead;
                            lookahead = (char) read.read();
                            if (lookahead == '\'') {
                                lexeme[i++] = lookahead;
                                state = 0;
                                lexeme[i] = '\0';
                                print(lexeme, "Char-Literal", 0, i);
                                i = 0;
                                lookahead = (char) read.read();
                            } else {
                                state = 0;
                                read.reset();
                                lexeme[i] = '\0';
                                print(lexeme, "Single-Qutation", 0, 1);
                                i = 0;
                                lookahead = (char) read.read();
                            }
                        } else {
                            state = 0;
                            read.reset();
                            lexeme[i] = '\0';
                            print(lexeme, "Single-Qutation", 0, 1);
                            i = 0;
                            lookahead = (char) read.read();
                        }
                    } else {
                        lexeme[i++] = lookahead;
                        if (lookahead == '\'') {
                            for (int k = 0; k < 2; k++) {
                                print(lexeme, "Single-Qutation", 0, 1);
                            }
                            state = 0;
                            i = 0;
                            lookahead = (char) read.read();
                        } else {
                            lookahead = (char) read.read();
                            if (lookahead == '\'') {
                                lexeme[i++] = lookahead;
                                state = 0;
                                lexeme[i] = '\0';
                                print(lexeme, "Char-Literal", 0, i);
                                i = 0;
                                lookahead = (char) read.read();
                            } else {
                                state = 0;
                                read.reset();
                                lexeme[i] = '\0';
                                print(lexeme, "Single-Qutation", 0, 1);
                                i = 0;
                                lookahead = (char) read.read();
                            }
                        }
                    }
                    break;

                case 63://done + checked 
                    lookahead = (char) read.read();
                    if (lookahead == '\n' || lookahead == '\r' || lookahead == '\uffff') {
                        state = 0;
                        read.reset();
                        lexeme[i] = '\0';
                        print(lexeme, "Duoble-Qutation", 0, 1);
                        i = 0;
                        lookahead = (char) read.read();
                    } else if (lookahead == '"') {
                        if (lexeme[i - 1] == '\\') {
                            lexeme[i - 1] = '"';
                            state = 63;
                        } else {
                            lexeme[i++] = lookahead;
                            state = 0;
                            lexeme[i] = '\0';
                            print(lexeme, "String-Literal", 0, i);
                            i = 0;
                            lookahead = (char) read.read();
                        }
                    } else {
                        lexeme[i++] = lookahead;
                        state = 63;
                    }
                    break;
                case 68://done + checked
                    lookahead = (char) read.read();
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "Comma", 0, i);
                    i = 0;
                    break;
                case 69://done + checked 
                    lookahead = (char) read.read();
                    if (Character.isDigit(lookahead)) {
                        state = 70;
                        lexeme[i++] = lookahead;
                    } else {
                        state = 71;
                        lexeme[i] = '\0';
                        print(lexeme, "Full-Stop", 0, i);
                        i = 0;
                    }
                    break;
                case 70://done + checked 
                    lookahead = (char) read.read();
                    if (Character.isDigit(lookahead)) {
                        state = 70;
                        lexeme[i++] = lookahead;
                    } else {
                        state = 0;
                        lexeme[i] = '\0';
                        print(lexeme, "Double-Literal", 0, i);
                        i = 0;
                    }
                    break;
                case 71://done + checked 
                    state = 0;
                    break;
                case 72://done + checked
                    lookahead = (char) read.read();
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "Right-Square bracket", 0, i);
                    i = 0;
                    break;
                case 73://done + checked
                    lookahead = (char) read.read();
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "Question_Mark", 0, i);
                    i = 0;
                    break;
                case 74://done + checked
                    lookahead = (char) read.read();
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "Left-Square bracket", 0, i);
                    i = 0;
                    break;
                case 75://done + checked
                    lookahead = (char) read.read();
                    state = 0;
                    lexeme[i] = '\0';
                    print(lexeme, "semi-colon", 0, i);
                    i = 0;
                    break;
                case 100:

                    print(lexeme, "UNRECOGNIZED_TOKEN", 0, 1);
                    lookahead = (char) read.read();
                    state = 0;
                    i = 0;
                default:

            }//end of switch

        }

    }

    public static int checkReserveWord(String[] ReservedWords, char[] a, int index) {

        char[] b = new char[index];
        for (int i = 0; i < b.length; i++) {
            b[i] = a[i];
        }

        String check = String.valueOf(b);
        for (int i = 0; i < ReservedWords.length; i++) {
            if (ReservedWords[i].equals(check)) {
                return 1;
            }
        }
        return 0;
    }

    public static void print(char[] lexeme, String s, int flag, int index) {
        if (flag == 1) {
            print(lexeme, lexeme);
        } else {
            char[] b = new char[index];
            for (int i = 0; i < b.length; i++) {
                b[i] = lexeme[i];
            }
            String st = String.valueOf(b);
            System.out.printf("%-53s %s", st, s);
            System.out.println("");
        }

    }

    public static void print(char[] lexeme, char[] c) {

        char[] ca = new char[15];
        for (int i = 0; i < lexeme.length; i++) {
            if (lexeme[i] != '\0') {
                ca[i] = lexeme[i];
            } else {
                break;
            }
        }

        String st = String.valueOf(ca);
        String trim = st.trim();
        System.out.printf("%-53s %s", trim, trim);
        System.out.println();

    }
}
