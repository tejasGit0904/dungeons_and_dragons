package dungeons_and_dragons.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import dungeons_and_dragons.model.CampaignModel;
import dungeons_and_dragons.model.GameMapModel;

/**
 * @author Hirangi Naik and Tejas Sadrani
 *
 */

public class CampaignView extends JFrame implements Observer, View {

	/**
	 * this variable used to set window title
	 * 
	 * @type String
	 */
	private String window_title = "Create Campaign";
	public JLabel campaign_label;
	private JLabel[] campaign_maps_label;
	public JComboBox campaign_combobox;
	private ArrayList<GameMapModel> maps;
	private Object[] campaign_array;
	public JButton campaign_add;
	public ArrayList<GameMapModel> campaign_map_list = new ArrayList<>();
	BufferedImage arrow_image;
	
	
	public CampaignView(ArrayList<GameMapModel>  maps) {

		this.maps = maps;
		
		// Initialize Campaign window
		this.setTitle(this.window_title);
		this.setPreferredSize(new Dimension(320, 510));
		this.setResizable(false);
		this.setLayout(null);
		campaign_array = this.maps.toArray();
		campaign_combobox = new JComboBox(campaign_array);
		
		
		this.updateWindow();
		
		// close frame while user click on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try{
			arrow_image = ImageIO.read(new File("res/ArrowDown04-128.png"));
		}catch(Exception e){
			
		}
		
	}

	private void updateWindow() {

		campaign_label = new JLabel("Select Map");
		campaign_label.setBounds(10, 10, 100, 25);
		this.add(campaign_label);
		
		if (campaign_array.length > 0)
			{
				campaign_combobox.setRenderer(new CampaignViewRenderer());
			}
		campaign_combobox.setBounds(150, 10, 150, 25);
		this.add(campaign_combobox);
		
		campaign_add=new JButton("Add map to Campaign");
		campaign_add.setBounds(50, 50, 150, 25);
		this.add(campaign_add);
		
		campaign_maps_label = new JLabel[campaign_map_list.size()*2];
		
		for(int i=0;i<campaign_map_list.size();i++){
			
			campaign_maps_label[i] = new JLabel((campaign_map_list.get(i).getMap_name()));
			//campaign_maps_label[i].setText((campaign_map_list.get(i).getMap_name()));
			campaign_maps_label[i+1] = new JLabel((new ImageIcon(arrow_image)));
			this.add(campaign_maps_label[i]);
			this.add(campaign_maps_label[i+1]);
			i = i+1;
		}
		
		// Display the window.
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	
	/**
	 * Invoked upon by Game map Model(observer) object once the data in width and height of first game view is set
	 */
	@Override
	public void update(Observable obs, Object obj) {
		
		campaign_map_list = ((CampaignModel)obs).getMap_list();
		
		for(int i= 0; i<campaign_map_list.size();i++)
		{
			campaign_combobox.removeItem(campaign_map_list.get(i));
		}
		this.updateWindow();
		
	}
	
	
	
	@Override
	public void setActionListener(ActionListener actionListener) {
		this.campaign_add.addActionListener(actionListener);
	}
	
	class CampaignViewRenderer extends BasicComboBoxRenderer {
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			GameMapModel map_model = (GameMapModel) value;
			setText(map_model.getMap_name());

			return this;
		}
	}

}
