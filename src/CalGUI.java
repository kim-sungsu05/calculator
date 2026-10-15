import javax.swing.*; // *는 하위 패키지까지 불러오는 게 아니라 이름만으로 클래스 사용 가능을 의미
import java.awt.*;

// Jframe - javax.swing의 class (GUI 프로그램의 메인 창 역할)
public class CalGUI extends JFrame {
    JButton b;
    JTextField t;
    JButton clear;

    public CalGUI() {
        FlowLayout layout = new FlowLayout(); // 배치관리자 - 자동으로 순서대로 배치
        this.setLayout(layout); // 현재 창(클래스)에 배치관리자 적용
        t = new JTextField(10); // 대략 10정도의 폭으로 입력창 생성
        b = new JButton("계산"); // 표시할 글자를 "계산" 으로하고 버튼 생성
        clear = new JButton("C");
        clear.setBackground(Color.RED); // 삭제 버튼 배경색 빨강으로 지정
        
        // 구성 요소 추가
        this.add(t);
        this.add(b);
        this.add(clear);

        this.setSize(320, 300); // 창의 크기
        this.setLocation(300, 300); // 창 위치
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 끄면 프로그램 종료
        this.setVisible(true); // 창을 화면에 표시
    }

    // 계산기 숫자 (0 ~ 9) GUI에 생성하는 메서드
    public AbstractButton[] createNums() {
        AbstractButton[] numButtons = new AbstractButton[10];

        for (int i = 0; i < 10; i++) {
            numButtons[i] = new JButton(String.valueOf(i));

            numButtons[i].setSize(50, 50);
            this.add(numButtons[i]);
        }
        return numButtons;
    }

    // 연산자 (+, -, *, /, =) 를 GUI에 생성하는 메서드
    public AbstractButton[] createOperator() {
        char[] operators = {'+', '-', 'x', '/', '='};
        AbstractButton[] operatorButtons = new AbstractButton[10];

        for (int i = 0; i < 5; i++) {
            operatorButtons[i] = new JButton(String.valueOf(operators[i]));

            operatorButtons[i].setSize(50, 50);
            this.add(operatorButtons[i]);
        }
        return operatorButtons;
    }
}
