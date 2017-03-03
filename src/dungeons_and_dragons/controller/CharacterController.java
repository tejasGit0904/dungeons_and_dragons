package dungeons_and_dragons.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dungeons_and_dragons.model.CharacterModel;
import dungeons_and_dragons.model.ItemModel;
import dungeons_and_dragons.view.CharacterView;

/**
 * The CharacterController translates the user's interactions with the
 * CharacterView into actions that the CharcaterModel will perform that may use
 * some additional/changed data gathered in a user-interactive view.
 * 
 * @author Hirangi Naik
 */
public class CharacterController implements ActionListener {

	private CharacterModel model;
	private CharacterView view;

	/**
	 * Default constructor of character controller
	 * <p>
	 * Character model and view are initialized and also view is binded to
	 * observer.
	 * <p>
	 * all the events of view are registered in constructor
	 */
	public CharacterController() {
		// create character Model object
		this.model = new CharacterModel();
		this.view = new CharacterView();

		this.model.addObserver(view);
		this.view.setActionListener(this);
		this.view.setVisible(true);
	}

	/**
	 * Parameterized constructor used for updating the character
	 * 
	 * @param characterModel
	 */
	public CharacterController(CharacterModel characterModel) {

		this.model = characterModel;
		this.view = new CharacterView(characterModel);
		this.model.addObserver(view);
		this.view.setActionListener(this);
		this.view.setVisible(true);
	}

	/**
	 * Action event of all the events
	 * 
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(view.back)) {
			this.backToCreateGame();
		} else if (e.getSource().equals(view.update)) {
			if (this.view.charactername_textfield.getText().equals("")) {
				JOptionPane.showMessageDialog(new JFrame(), "Please enter character name");
				return;
			} else {
				model.setCharacter_name(this.view.charactername_textfield.getText());
			}
			int level = 0;
			try {
				int i = Integer.parseInt(this.view.level_textfield.getText());
				model.setCharacter_level(i);
				ItemModel armer = (ItemModel) this.view.armer_combobox.getSelectedItem();
				ItemModel belt = (ItemModel) this.view.belt_combobox.getSelectedItem();
				ItemModel boot = (ItemModel) this.view.boot_combobox.getSelectedItem();
				ItemModel helmet = (ItemModel) this.view.helmet_combobox.getSelectedItem();
				ItemModel ring = (ItemModel) this.view.ring_combobox.getSelectedItem();
				ItemModel weapon = (ItemModel) this.view.weapon_combobox.getSelectedItem();
				ItemModel shield = (ItemModel) this.view.shield_combobox.getSelectedItem();

				ArrayList<ItemModel> items = new ArrayList<ItemModel>();
				if (armer != null)
					items.add(armer);
				if (belt != null)
					items.add(belt);
				if (boot != null)
					items.add(boot);
				if (helmet != null)
					items.add(helmet);
				if (ring != null)
					items.add(ring);
				if (weapon != null)
					items.add(weapon);
				if (shield != null)
					items.add(shield);
				this.model.setItems(items);

				ArrayList<ItemModel> backPackList = new ArrayList<ItemModel>();

				if (this.view.backPackList.getSelectedValuesList().size() > 0) {

					backPackList = (ArrayList<ItemModel>) this.view.backPackList.getSelectedValuesList();
				}
				this.model.setBackPackItems(backPackList);

				this.model.calculateModifires();
				this.model.calculateHitPoints(level);
				this.model.calculateArmorClass();
				this.model.calculateAttackBonus(level);
				this.model.calculateDamageBonus();

				model.update();
				new ManageCharacterController();
				this.view.dispose();

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(new JFrame(), "Please enter valid level");
			}

		} else if (e.getSource().equals(view.rolldice_save)) {
			String character_name = this.view.charactername_textfield.getText();
			// model.setCharacter_name(character_name);
			if (character_name.equals("")) {
				JOptionPane.showMessageDialog(new JFrame(), "Please enter character name");
				return;
			} else {
				this.model.setCharacter_name(character_name);
			}
			try {
				int level = Integer.parseInt(this.view.level_textfield.getText());
				this.model.setCharacter_level(level);
				ItemModel armer = (ItemModel) this.view.armer_combobox.getSelectedItem();
				ItemModel belt = (ItemModel) this.view.belt_combobox.getSelectedItem();
				ItemModel boot = (ItemModel) this.view.boot_combobox.getSelectedItem();
				ItemModel helmet = (ItemModel) this.view.helmet_combobox.getSelectedItem();
				ItemModel ring = (ItemModel) this.view.ring_combobox.getSelectedItem();
				ItemModel weapon = (ItemModel) this.view.weapon_combobox.getSelectedItem();
				ItemModel shield = (ItemModel) this.view.shield_combobox.getSelectedItem();

				ArrayList<ItemModel> items = new ArrayList<ItemModel>();
				if (armer != null)
					items.add(armer);
				if (belt != null)
					items.add(belt);
				if (boot != null)
					items.add(boot);
				if (helmet != null)
					items.add(helmet);
				if (ring != null)
					items.add(ring);
				if (weapon != null)
					items.add(weapon);
				if (shield != null)
					items.add(shield);
				this.model.setItems(items);

				ArrayList<ItemModel> backPackList = new ArrayList<ItemModel>();

				if (this.view.backPackList.getSelectedValuesList().size() > 0) {

					backPackList = (ArrayList<ItemModel>) this.view.backPackList.getSelectedValuesList();
				}

				this.model.setBackPackItems(backPackList);

				this.model.calculateAbilityScores();
				this.model.calculateModifires();
				this.model.calculateHitPoints(level);
				this.model.calculateArmorClass();
				this.model.calculateAttackBonus(level);
				this.model.calculateDamageBonus();

				model.save();

				new ManageCharacterController();
				this.view.dispose();

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(new JFrame(), "Please enter valid level");
			}
		}
	}

	/**
	 * method to dispose current view and go back to previous view
	 */
	private void backToCreateGame() {
		new CreateGameController();
		view.dispose();
	}

}