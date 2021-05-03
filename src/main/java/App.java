import java.util.Scanner;


/*
필요한거

회차
득점
타자 투수 누구냐


 */
public class App {
    static final String PROMPT = "KBO>> ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] buf;


        Board cur = new Board();
        Inning curI = Inning.TOP_AWAY;

        System.out.println("지금은 홈팀만 가능");

        while (true) {
            System.out.println("지금 공격중인 팀 : " + curI.name());
            System.out.println("S / B / H / O");
            System.out.print(PROMPT);
            //buf = sc.nextLine().split(" ");
            char t = sc.next().charAt(0);
            //char t = buf[0].charAt(0);

            //행동
            if (t == 'S' || t == 's') {
                cur.strike++;
            } else if (t == 'B' || t == 'b') {
                cur.ball++;
            } else if (t == 'H' || t == 'h') {
                cur.hit++;
                cur.strike = 0;
                cur.ball = 0;

            } else if (t == 'O' || t == 'o') {
                cur.out++;
            } else {

            }

            //점검
            if (cur.strike >= 3) {
                System.out.println("스트라이크3 아웃");
                cur.strike = 0;
                cur.out++;

            }
            if (cur.ball >= 4) {
                System.out.println("볼넷 진루");
                cur.ball = 0;
            }
            if (cur.out >= 3) {
                System.out.println("공수교대 : 빠빠빠빠빰 빠빠빠빠");
                curI = Inning.change(curI);
                cur.out = 0;
            }

            System.out.print(PROMPT + cur.toString());
            System.out.println("\n\r\n");
        }
    }


}

enum Inning {
    TOP_AWAY,BOT_HOME;
    /*
    TOP_AWAY(0),
    BOT_HOME(1);
    private int state;



    Inning(int state) {
        state = state;
    }

    public int getState() {
        return state;
    }

    public void change() {
        this.state = 1 - state;
    }
     */
    public static Inning change(Inning i) {
        if(i == Inning.TOP_AWAY) {
            return Inning.BOT_HOME;
        }
        return Inning.TOP_AWAY;
    }
}

class Board {
    int strike;
    int ball;
    int hit;
    int out;

    public Board() {
        this.strike = 0;
        this.ball = 0;
        this.hit = 0;
        this.out = 0;
    }

    @Override
    public String toString() {
        return "Board{" +
                "strike=" + strike +
                ", ball=" + ball +
                ", hit=" + hit +
                ", out=" + out +
                '}';
    }
}
