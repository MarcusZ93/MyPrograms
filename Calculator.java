/*En este programa realizamos una calculadora y vamos a ir explicando paso a paso su  funcionamiento


        Mi deseo es que puedan comprenderlo a la perfección , cualquier consulta al inbox


 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Calculator
{

        public static void main(String[] args)
        {

                FrameCalculator myCalculator = new FrameCalculator();


        }


}


class FrameCalculator extends JFrame
{

        public FrameCalculator()
        {

                setTitle("Calculator");

                setBounds(500 , 300 , 450 , 300);

                add(new SheetCalculator());

                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                setVisible(true);


        }

}


class SheetCalculator extends JPanel
{


        public SheetCalculator()
        {

                firstNumber = true;

                setLayout(new BorderLayout());

                window = new JButton("Presione un numero.");

                window.setEnabled(false);

                add(window , BorderLayout.NORTH);

                //**********************************************************
                //Creamos la lamina del centro :

                sheetCenter = new JPanel();

                sheetCenter.setLayout(new GridLayout(4,4));

                setAllButtons();

                add(sheetCenter);

                lastOperation = "=" ;


        }


        public void setAllButtons()
        {

                ActionListener insert = new InsertNumber();
                ActionListener operationMath = new ActionMath();


                setButtons( "7" , insert  );
                setButtons("8" , insert  );
                setButtons( "9" , insert  );
                setButtons("X" , operationMath  );


                setButtons( "4" , insert );
                setButtons("5" , insert );
                setButtons("6" , insert );
                setButtons( "÷" , operationMath);


                setButtons( "1" , insert );
                setButtons( "2" , insert );
                setButtons("3" , insert );
                setButtons("-" , operationMath );


                setButtons("0" , insert );
                setButtons("+" , operationMath );
                setButtons("." , insert);
                setButtons("=" , operationMath );


        }


        private void setButtons( String nameButton , ActionListener listen )
                {

                        JButton button = new JButton(nameButton);

                        button.addActionListener(listen);

                        button.addKeyListener(myMacro);

                        sheetCenter.add(button);


                }

        private EventsKey myMacro = new EventsKey();

        private JButton myButtons;

        private final JPanel sheetCenter;


        private class InsertNumber implements ActionListener
        {


                @Override
                public void actionPerformed(ActionEvent actionEvent)
                {

                        String in = actionEvent.getActionCommand();


                        if(firstNumber)
                        {
                                window.setText("");
                                firstNumber = false;
                        }


                        window.setText(window.getText() + in);

                }


        }

        private boolean firstNumber;

        private final JButton window;

        private double result;

        private String lastOperation;

        private class ActionMath implements ActionListener
        {


                @Override
                public void actionPerformed(ActionEvent actionEvent)
                {

                        String operation = actionEvent.getActionCommand();

                        calculate( Double.parseDouble(window.getText()));

                        lastOperation = operation;

                        firstNumber = true;


                }


                public void calculate(double number)
                {

                        if(lastOperation.equals("+"))

                                result += number;


                        else if (lastOperation.equals("-"))

                                result -= number;


                        else if (lastOperation.equals("÷"))

                                result /= number;


                        else if (lastOperation.equals("X"))

                                result *= number;


                        else if (lastOperation.equals("="))

                                result = number;


                        window.setText(String.valueOf(result));


                }


        }

        private class EventsKey extends KeyAdapter
        {

                private final String [] CARACTER_KEYS = new String[]{"0","1","2","3","4","5","6","7","8","9", "*" , "/" , "=" };

                @Override
                public void keyTyped(KeyEvent keyEvent)
                {

                        char keyChar = keyEvent.getKeyChar();

                        String valor = String.valueOf(keyChar); //Pasamos el dato a String para poder tratarlo correctamente

                        if(firstNumber)
                        {
                                window.setText("");
                                firstNumber = false;
                        }


                        setKeyChar(valor);


                }

                public void setKeyChar(String keyChar)
                {
                        for(int i = 0 ; i < CARACTER_KEYS.length ; i++)
                        {


                                if(keyChar.equalsIgnoreCase(CARACTER_KEYS[i]))
                                {

                                        window.setText( window.getText() + keyChar);

                                }

                        }
                }


        }

}

