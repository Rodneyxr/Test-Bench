package core;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;

public class WebsitePicker extends JApplet {
	private static final long serialVersionUID = 1L;

	private HashMap<String, URL> websiteInfo;
	private ArrayList<String> titles;
	private JList<?> mainList;

	public void init() {
		titles = new ArrayList<String>();
		Collections.fill(titles, "Hello");
		mainList = new JList<Object>(titles.toArray());
		mainList.addListSelectionListener(e -> {
			System.out.println("selected");
		});

		this.setLayout(new FlowLayout());
		this.add(new JLabel("What website do you want to visit?"));
		this.add(actionButton("Click Me", e -> {
			((JButton) e.getSource()).setEnabled(false);
			JDialog dialog = new JDialog();
			dialog.setTitle("Button Clicked");
			dialog.add(label("You Clicked the button!"), BorderLayout.NORTH);
			dialog.add(actionButton("Okay", event -> {
				dialog.dispose();
			}));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.pack();
			dialog.setVisible(true);
		}));

		this.add(mainList);
	}

	public static JLabel label(String message) {
		return new JLabel(message);
	}

	public static JButton actionButton(String text, ActionListener l) {
		JButton actionButton = new JButton(text);
		actionButton.addActionListener(l);
		return actionButton;
	}

}
