public class GUI()
{
  GUI()
  {
    JFrame main = new JFrame();
    JPanel output_panel=new JPanel();
    JPanel options=new Jpanel();
    JPanel header=new JPanel();
    JLabel title = new JLabel("DBLP Query Engine");
    header.add(title);
    main.add(header);
    main.add(output_panel);
    main.add(options);
  }
}
