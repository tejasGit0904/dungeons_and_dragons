/**
 * 
 */
package dungeons_and_dragons.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.ItemModel;

/**
 * @author Mihir Pujara
 *
 */
public class CharacterInventoryView extends JFrame implements View {

	private JPanel panel;

	public JList<ItemModel> itemList;

	public JList<ItemModel> backPackList;

	public JButton moveFromItemToBack;

	public JButton moveFromBackToItem;

	public JButton okButton;

	private CharacterModel character;

	public JScrollPane backPackScrollPane;

	public JScrollPane itemScrollPane;

	private JLabel nameLabel;

	private JLabel levelLabel;
	
	private JLabel hitPointsLabel; 
	
	private JLabel armorClassLabel; 
	
	private JLabel attackBonusLabel; 
	
	private JLabel damageBonusLabel;
	
	private JLabel abilityScoreLabel;
	
	private JLabel modifierLabel;
	
	private JLabel strengthLabel;

	private JLabel dexterityLabel;
	
	private JLabel constitutionLabel;
	
	private JLabel intelligenceLabel;
	
	private JLabel wisdomLabel;
	
	private JLabel charismaLabel;
	
	private JLabel strengthASLabel;

	private JLabel dexterityASLabel;
	
	private JLabel constitutionASLabel;
	
	private JLabel intelligenceASLabel;
	
	private JLabel wisdomASLabel;
	
	private JLabel charismaASLabel;
	
	private JLabel strengthMLabel;

	private JLabel dexterityMLabel;
	
	private JLabel constitutionMLabel;
	
	private JLabel intelligenceMLabel;
	
	private JLabel wisdomMLabel;
	
	private JLabel charismaMLabel;

	public CharacterInventoryView(CharacterModel character) {

		this.character = character;

		this.setTitle("Character Inventory");

		this.initilizeView();

		this.setResizable(false);

		this.pack();
		this.setLocationRelativeTo(null);
	}

	public void initilizeView() {

		this.panel = new JPanel();
		this.panel.setLayout(null);
		this.panel.setPreferredSize(new Dimension(500, 400));

		this.showDetails(this.character);

		// item list

		this.itemList = new JList<ItemModel>();

		ArrayList<ItemModel> temp = this.character.getItems();
		ArrayList<ItemModel> item = new ArrayList<ItemModel>();

		if ((temp != null) && (temp.size() > 0)) {

			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i) != null)
					item.add(temp.get(i));
			}
		}

		if ((item != null) || (item.size() > 0)) {

			ItemModel[] items = new ItemModel[item.size()];

			for (int i = 0; i < item.size(); i++) {
				if (item.get(i) != null)
					items[i] = item.get(i);
			}

			this.itemList.setListData(items);
			this.itemList.setCellRenderer(new ItemCellRenderer());
		}

		this.itemScrollPane = new JScrollPane(this.itemList);
		this.itemScrollPane.setBounds(10, 150, 200, 200);
		this.panel.add(this.itemScrollPane);

		// backpack list

		this.backPackList = new JList<ItemModel>();
		this.backPackList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		temp = this.character.getBackPackItems();

		ArrayList<ItemModel> backPackItem = new ArrayList<ItemModel>();
		if ((temp != null) && (temp.size() > 0)) {
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i) != null)
					backPackItem.add(temp.get(i));
			}
		}

		if ((backPackItem != null) || (backPackItem.size() > 0)) {

			ItemModel[] items = new ItemModel[backPackItem.size()];

			for (int i = 0; i < backPackItem.size(); i++) {
				if (backPackItem.get(i) != null)
					items[i] = backPackItem.get(i);
			}

			this.backPackList.setListData(items);
			this.backPackList.setCellRenderer(new ItemCellRenderer());
		}

		this.backPackScrollPane = new JScrollPane(this.backPackList);
		this.backPackScrollPane.setBounds(290, 150, 200, 200);
		this.panel.add(this.backPackScrollPane);

		// move to item to backpack >

		this.moveFromItemToBack = new JButton(">");
		this.moveFromItemToBack.setBounds(225, 170, 50, 50);

		this.panel.add(this.moveFromItemToBack);

		// move to backpack to item <

		this.moveFromBackToItem = new JButton("<");
		this.moveFromBackToItem.setBounds(225, 280, 50, 50);

		this.panel.add(this.moveFromBackToItem);

		// ok button

		this.okButton = new JButton("OK");
		this.okButton.setBounds(217, 360, 65, 30);

		this.panel.add(this.okButton);

		this.getContentPane().add(this.panel);
	}

	public void showDetails(CharacterModel character) {

		// name
		
		nameLabel = new JLabel("Name: " + character.getCharacter_name());
		nameLabel.setBounds(10, 5, 100, 20);
		this.panel.add(nameLabel);

		// level
		
		levelLabel = new JLabel("Level: " + character.getCharacter_level());
		levelLabel.setBounds(10, 25, 100, 20);
		this.panel.add(levelLabel);
		
		// hit points
		
		hitPointsLabel = new JLabel("Hit Points: " + character.getHitpoints());
		hitPointsLabel.setBounds(10, 45, 100, 20);
		this.panel.add(hitPointsLabel);
		
		// armor class
		
		armorClassLabel = new JLabel("Armor Class: "+ character.getArmorClass());
		armorClassLabel.setBounds(10, 65, 100, 20);
		this.panel.add(armorClassLabel);
		
		// attack bonus
		
		attackBonusLabel = new JLabel("Attack Bonus: "+ character.getAttackBonus());
		attackBonusLabel.setBounds(10, 85, 100, 20);
		this.panel.add(attackBonusLabel);
		
		// damage bonus
		
		damageBonusLabel = new JLabel("Damage Bonus: "+ character.getDamageBonus());
		damageBonusLabel.setBounds(10, 105, 100, 20);
		this.panel.add(damageBonusLabel);
		
		
		abilityScoreLabel = new JLabel("Ability Score");
		abilityScoreLabel.setBounds(300, 5, 100, 20);
		this.panel.add(abilityScoreLabel);
		
		modifierLabel = new JLabel("Modifiers");
		modifierLabel.setBounds(400, 5, 100, 20);
		this.panel.add(modifierLabel);
		
		strengthLabel = new JLabel("Strength");
		strengthLabel.setBounds(210, 20, 100, 20);
		this.panel.add(strengthLabel);

		dexterityLabel = new JLabel("Dexterity");
		dexterityLabel.setBounds(210, 40, 100, 20);
		this.panel.add(dexterityLabel);
		
		constitutionLabel = new JLabel("Constitution");
		constitutionLabel.setBounds(210, 60, 100, 20);
		this.panel.add(constitutionLabel);
		
		intelligenceLabel = new JLabel("Intelligence");
		intelligenceLabel.setBounds(210, 80, 100, 20);
		this.panel.add(intelligenceLabel);
		
		wisdomLabel = new JLabel("Wisdom");
		wisdomLabel.setBounds(210, 100, 100, 20);
		this.panel.add(wisdomLabel);
		
		charismaLabel = new JLabel("Charisma");
		charismaLabel.setBounds(210, 120, 100, 20);
		this.panel.add(charismaLabel);
		
		
		strengthASLabel = new JLabel(character.getAbilityScores().getStraight()+"");
		strengthASLabel.setBounds(330, 20, 100, 20);
		this.panel.add(strengthASLabel);

		dexterityASLabel = new JLabel(character.getAbilityScores().getDexterity()+"");
		dexterityASLabel.setBounds(330, 40, 100, 20);
		this.panel.add(dexterityASLabel);
		
		constitutionASLabel = new JLabel(character.getAbilityScores().getConstitution()+"");
		constitutionASLabel.setBounds(330, 60, 100, 20);
		this.panel.add(constitutionASLabel);
		
		intelligenceASLabel = new JLabel(character.getAbilityScores().getIntelligence()+"");
		intelligenceASLabel.setBounds(330, 80, 100, 20);
		this.panel.add(intelligenceASLabel);
		
		wisdomASLabel = new JLabel(character.getAbilityScores().getWisdom()+"");
		wisdomASLabel.setBounds(330, 100, 100, 20);
		this.panel.add(wisdomASLabel);
		
		charismaASLabel = new JLabel(character.getAbilityScores().getCharisma()+"");
		charismaASLabel.setBounds(330, 120, 120, 20);
		this.panel.add(charismaASLabel);
		
		
		strengthMLabel = new JLabel(character.getModifiers().getStraight()+"");
		strengthMLabel.setBounds(420, 20, 100, 20);
		this.panel.add(strengthMLabel);

		dexterityMLabel = new JLabel(character.getModifiers().getDexterity()+"");
		dexterityMLabel.setBounds(420, 40, 100, 20);
		this.panel.add(dexterityMLabel);
		
		constitutionMLabel = new JLabel(character.getModifiers().getConstitution()+"");
		constitutionMLabel.setBounds(420, 60, 100, 20);
		this.panel.add(constitutionMLabel);
		
		intelligenceMLabel = new JLabel(character.getModifiers().getIntelligence()+"");
		intelligenceMLabel.setBounds(420, 80, 100, 20);
		this.panel.add(intelligenceMLabel);
		
		wisdomMLabel = new JLabel(character.getModifiers().getWisdom()+"");
		wisdomMLabel.setBounds(420, 100, 100, 20);
		this.panel.add(wisdomMLabel);
		
		charismaMLabel = new JLabel(character.getModifiers().getCharisma()+"");
		charismaMLabel.setBounds(420, 120, 100, 20);
		this.panel.add(charismaMLabel);
	}

	public void updateList(CharacterModel character) {

		this.itemList.removeAll();
		ArrayList<ItemModel> temp = character.getItems();
		ArrayList<ItemModel> item = new ArrayList<ItemModel>();

		if ((temp != null) && (temp.size() > 0)) {

			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i) != null)
					item.add(temp.get(i));
			}
		}

		if ((item != null) || (item.size() > 0)) {

			ItemModel[] items = new ItemModel[item.size()];

			for (int i = 0; i < item.size(); i++) {
				if (item.get(i) != null)
					items[i] = item.get(i);
			}

			this.itemList.setListData(items);
		}

		this.backPackList.removeAll();

		temp = character.getBackPackItems();

		ArrayList<ItemModel> backPackItem = new ArrayList<ItemModel>();
		if ((temp != null) && (temp.size() > 0)) {
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i) != null)
					backPackItem.add(temp.get(i));
			}
		}

		if ((backPackItem != null) || (backPackItem.size() > 0)) {

			ItemModel[] items = new ItemModel[backPackItem.size()];

			for (int i = 0; i < backPackItem.size(); i++) {
				if (backPackItem.get(i) != null)
					items[i] = backPackItem.get(i);
			}

			this.backPackList.setListData(items);
		}

	}

	class ItemCellRenderer extends JLabel implements ListCellRenderer<ItemModel> {

		private static final long serialVersionUID = 1L;

		public ItemCellRenderer() {
			setOpaque(true);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends ItemModel> arg0, ItemModel arg1, int arg2,
				boolean arg3, boolean arg4) {

			if (arg1 != null) {
				setText(arg1.getItem_name());
				setSize(200, 30);
			}

			if (arg3) {
				setBackground(new Color(0, 0, 128));
				setForeground(Color.white);
			} else {
				setBackground(Color.white);
				setForeground(Color.black);
			}

			return this;
		}
	}

	@Override
	public void setActionListener(ActionListener actionListener) {

		this.moveFromItemToBack.addActionListener(actionListener);

		this.moveFromBackToItem.addActionListener(actionListener);

		this.okButton.addActionListener(actionListener);
	}
}
