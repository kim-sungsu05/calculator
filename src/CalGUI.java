import javax.swing.*; // *는 하위 패키지까지 불러오는 게 아니라 이름만으로 클래스 사용 가능을 의미
import java.awt.*;

// Jframe - javax.swing의 class (GUI 프로그램의 메인 창 역할)
public class CalGUI extends JFrame {
    JButton b;
    JTextField t;
    JButton clear;

    double firstNumber; // 첫번째 숫자 저장
    char selectedOperator = '\0'; // 선택한 연산자 저장

    public CalGUI() {
        FlowLayout layout = new FlowLayout(); // 배치관리자 - 자동으로 순서대로 배치
        this.setLayout(layout); // 현재 창(클래스)에 배치관리자 적용
        t = new JTextField(30); // 대략 30정도의 폭으로 입력창 생성
        b = new JButton("계산"); // 표시할 글자를 "계산" 으로하고 버튼 생성
        clear = new JButton("C");
        clear.setBackground(Color.RED); // 삭제 버튼 배경색 빨강으로 지정
        
        // 계산버튼에 이벤트 추가
        b.addActionListener(e -> {
            String input = t.getText();

            if (selectedOperator == '\0' || input.isEmpty()) {
                return;
            }

            double secondNumber = Double.parseDouble(input);
            double result;

            switch (selectedOperator) {
                case '+':
                    result = firstNumber + secondNumber;
                    break;

                case '-':
                    result = firstNumber - secondNumber;
                    break;

                case 'x':
                    result = firstNumber * secondNumber;
                    break;

                case '/':
                    if (secondNumber == 0) {
                        JOptionPane.showMessageDialog(this, "0으로 나눌 수 없습니다.");
                        return;
                    }
                    result = firstNumber / secondNumber;
                    break;

                default:
                    return;
            }

            t.setText(String.valueOf(result));
            selectedOperator = '\0';
        });
        
        // 삭제 버튼에 이벤트 추가
        clear.addActionListener(e -> {
            t.setText("");
            firstNumber = 0;
            selectedOperator = '\0';
        });
        
        // 구성 요소 추가
        this.add(t);
        this.add(b);
        this.add(clear);

        this.setSize(400, 300); // 창의 크기
        this.setLocation(1400, 400); // 창 위치
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 끄면 프로그램 종료

        createNums();
        createOperator();
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
        
        // 각 버튼에 클릭 시 입력창에 뜨는 이벤트 부여
        for (int i = 0; i < numButtons.length; i++) {
            String digit = String.valueOf(i);

            numButtons[i].addActionListener(e -> {
                t.setText(t.getText() + digit); // 입력창의 현재 문자열을 가져와서 새로운 문자열 표시
            });
        }
        return numButtons;
    }

    // 연산자 (+, -, *, /, =) 를 GUI에 생성하는 메서드
    public AbstractButton[] createOperator() {
        char[] operators = {'+', '-', 'x', '/'};

        AbstractButton[] operatorButtons = new AbstractButton[4];

        for (int i = 0; i < 4; i++) {
            operatorButtons[i] = new JButton(String.valueOf(operators[i]));

            operatorButtons[i].setSize(50, 50);
            this.add(operatorButtons[i]);
        }
        
        // 연산자 버튼에 이벤트 추가
        for (int i = 0; i < operatorButtons.length; i++) {
            char op = operatorButtons[i].getText().charAt(0); // 버튼에 표시된 문자열을 가져와서 op에 저장

            operatorButtons[i].addActionListener(e -> {
                String input = t.getText();

                if (input.isEmpty()) {
                    return;
                }

                firstNumber = Double.parseDouble(input); // 입력창의 숫자를 실수형으로 변환
                selectedOperator = op; // 선택한 연산자 저장
                t.setText(""); // 입력창을 비운다
            });
        }

        return operatorButtons;
    }
}
