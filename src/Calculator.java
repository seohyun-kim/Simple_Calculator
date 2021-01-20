import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class Event implements ActionListener {
    private JTextField tf_1, tf_2;
    private JLabel l_1;

    public Event(JTextField tf1, JTextField tf2, JLabel l) {
        this.tf_1 = tf1;
        this.tf_2 = tf2;
        this.l_1 = l; // result 가 될 부분
    }

    public void actionPerformed(ActionEvent a)  {
        // Write code here!


        JButton btn =(JButton)a.getSource();

        String str1 = this.tf_1.getText().trim().toString();// 공백 제거해서  string 형태로 가져와
        String str2 = this.tf_2.getText().trim().toString();

        double result=0;//연산의 결과값이 될 변수 (0으로 초기화)

        //======================================================
        // 숫자 이외의 것이 입력되었을때 예외처리

        try {
           Double.parseDouble(str1);
        }
        catch(NumberFormatException e) {
            l_1.setText("Error: Illegal data on the 1st-field!");
            l_1.setForeground(Color.RED);
            return;  //두 필드에 모두 숫자가 아닌 값이 입력 될 경우 tf_1이 틀렸다고 출력됨
        }

        try {
            Double.parseDouble(str2);
        }
        catch(NumberFormatException e) {
            l_1.setText("Error: Illegal data on the 2st-field!");
            l_1.setForeground(Color.RED);
            return;
        }
        //==========================================================

        // return 이 되지 않았다는 것은 double 형태로 바꿀 수 있음 (위의 예외처리에 포함시켜 보려고했으나 실패했습니다ㅜㅜ)

        double num1= Double.parseDouble(str1);
        double num2= Double.parseDouble(str2);
        l_1.setForeground(Color.BLUE); //일단 디폴트로 지정

        //============================================================

        //연산자 버튼 클릭

        switch(btn.getText()) //이벤트가 발생한 버튼의 텍스트값을 받아와 비교
        {
            case "+" :
                result =num1+num2;
                l_1.setText(str1 + " + " + str2 + " = " +String.format("%.4f",result));
                break;

            case "-":
                result =num1-num2;
                l_1.setText(str1 + " - " + str2 + " = " +String.format("%.4f",result));
                break;

            case "*":
                result =num1*num2;
                l_1.setText(str1 + " * " + str2 + " = " +String.format("%.4f",result));
                break;

            case "/":
                if(num2==0) //ArithmeticException 에러에 걸리지 않아 if 문사용
                {
                    l_1.setText("Error: Cannot divide by zero!"); //두번째 인자가 0인경우 0으로 나눌 수 없음을 알림
                    l_1.setForeground(Color.RED); //Error 문은 빨간색으로 처리
                    break;
                }
                result=num1/num2;
                l_1.setText(str1 + " / " + str2 + " = " +String.format("%.4f",result));
                break;
        }
        return;
    }
}


class Calculator extends JFrame {
    private JButton b_add,b_subtract,b_multiply,b_divide;
    private JTextField tf_num1,tf_num2;
    private JLabel l_num1,l_num2,l_result,l_result_out;

    Calculator() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Label1: "1st"
        this.l_num1 = new JLabel("1st: ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(this.l_num1,c);

        // Field1
        this.tf_num1 = new JTextField(10);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 3;
        add(this.tf_num1,c);

        // Write code here!
        // Label2: "2nd"
         this.l_num2 = new JLabel("2nd: ");
         c.fill = GridBagConstraints.HORIZONTAL;
         c.gridx = 0;
         c.gridy = 1;
         add(this.l_num2,c);

        // Field2
         this.tf_num2 = new JTextField(10);
         c.fill = GridBagConstraints.HORIZONTAL;
         c.gridx = 1;
         c.gridy = 1;
         c.gridwidth = 3;
         add(this.tf_num2,c);

        // Button1: "+"
         this.b_add = new JButton("+");
          c.fill = GridBagConstraints.NONE;
          c.weightx=0.1;
          c.gridx = 0;
          c.gridy = 2;
          c.gridwidth = 1;
          this.b_add.setBackground(Color.GRAY);
          this.b_add.setForeground(Color.WHITE);
          add(this.b_add,c);

        // Button2: "-"
         this.b_subtract = new JButton("-");
          c.fill = GridBagConstraints.NONE;
          c.weightx=0.1;
          c.gridx = 1;
          c.gridy = 2;
          c.gridwidth = 1;
          this.b_subtract.setBackground(Color.LIGHT_GRAY);
          add(this.b_subtract,c);


        // Button3: "*"
         this.b_multiply = new JButton("*");
          c.fill = GridBagConstraints.NONE;
          c.weightx=0.1;
          c.gridx = 2;
          c.gridy = 2;
          c.gridwidth = 1;
          this.b_multiply.setBackground(Color.GRAY);
          this.b_multiply.setForeground(Color.WHITE);
          add(this.b_multiply,c);

        // Button4: "/"
         this.b_divide = new JButton("/");
          c.fill = GridBagConstraints.NONE;
          c.weightx=0.1;
          c.gridx = 3;
          c.gridy = 2;
          c.gridwidth = 1;
          this.b_divide.setBackground(Color.LIGHT_GRAY);
          add(this.b_divide,c);

        // Label3: "Result"
         this.l_result = new JLabel("Result: ");
          c.fill = GridBagConstraints.HORIZONTAL;
          c.gridx = 0;
          c.gridy = 3;
          add(this.l_result,c);

        // Label4: "Result-Out"
         this.l_result_out = new JLabel("");
          c.fill = GridBagConstraints.HORIZONTAL;
          c.gridx = 1;
          c.gridy = 3;
          c.gridwidth = 4; //에러코드가 길어서 길이를 4로함
         add(this.l_result_out,c);

        Event a = new Event(this.tf_num1, this.tf_num2, this.l_result_out);

         this.b_add.addActionListener(a);
         this.b_subtract.addActionListener(a);
         this.b_multiply.addActionListener(a);
         this.b_divide.addActionListener(a);
    }

    public static void main(String args[])
    {
        Calculator ob = new Calculator();
        ob.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ob.setTitle("Calculator");

        ob.setSize(400,200);
        ob.setVisible(true);
    }
}