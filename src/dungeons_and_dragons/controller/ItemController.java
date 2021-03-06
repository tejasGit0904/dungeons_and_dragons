package dungeons_and_dragons.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dungeons_and_dragons.decorator.Burning;
import dungeons_and_dragons.decorator.Freezing;
import dungeons_and_dragons.decorator.Frightening;
import dungeons_and_dragons.decorator.Pacifying;
import dungeons_and_dragons.decorator.Slaying;
import dungeons_and_dragons.decorator.WeaponType;
import dungeons_and_dragons.decorator.Weapons;
import dungeons_and_dragons.helper.Game_constants;
import dungeons_and_dragons.model.ItemModel;
import dungeons_and_dragons.view.ItemView;

/**
 * This class call item controller where it handles model and view
 * 
 * @author Urmil Kansara & Shahida Chauhan
 *
 */
public class ItemController implements ActionListener {

	/**
	 * This creates new model
	 * 
	 * @type ItemModel
	 */
	ItemModel item_model;

	/**
	 * This create observer object
	 * 
	 * @type ItemView
	 */
	ItemView item_view;

	/**
	 * Default constructor of item controller
	 * <p>
	 * Item model and view are initialized and also view is binded to observer.
	 * <p>
	 * all the events of view are registered in constructor
	 */
	public ItemController() {
		// this.field_item = item_field;
		this.item_model = new ItemModel();
		this.item_view = new ItemView();

		this.item_model.addObserver(item_view);
		this.item_view.setListener(this);
		this.item_view.setVisible(true);
	}

	/**
	 * This constructor is used for initializing of update task for items.
	 * 
	 * 
	 * @param itemModel
	 *            Item object to update item
	 */
	public ItemController(ItemModel itemModel) {

		this.item_model = itemModel;
		this.item_view = new ItemView(itemModel);
		this.item_model.addObserver(item_view);
		this.item_view.setListener(this);
		this.item_view.setVisible(true);
	}

	/**
	 * Action event to handle saving of item,change in combobox,going back to
	 * previous screen and updation of item
	 * 
	 * @param arg0
	 *            actionevent argument to control event
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource().equals(item_view.item_type_field)) {
			String get_item_type = (String) item_view.item_type_field.getSelectedItem();

			item_model.itemTypeSelected(get_item_type);

		} else if (arg0.getSource().equals(item_view.save_item)) {
			if (item_view.item_name_field.getText().equals("")) {
				JOptionPane.showOptionDialog(null, "Please Enter Valid name", "Invalid Name",
						JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[] {}, null);
				return;
			}
			try {
				String item_name = item_view.item_name_field.getText();
				item_model.setItem_name(item_name);
				String item_type = (String) item_view.item_type_field.getSelectedItem();
				item_model.setItem_type(item_type);
				String item_ability = (String) item_view.item_ability_field.getSelectedItem();
				item_model.setItem_ability(item_ability);
				int item_point = Integer.parseInt(item_view.item_score_field.getText());
				item_model.setItem_point(item_point);

				if (item_point <= 0 || item_point >= 6) {

					throw new NumberFormatException();
				}
				
				Weapons item_weapon = new WeaponType(item_point);
				if (item_view.item_weapon_enchantment_field.getSelectedItem() != null) {
					item_model.setItem_weapon_enchantment(
							item_view.item_weapon_enchantment_field.getSelectedItem().toString());
					// code for weapon enchantment
					
					if (item_view.item_weapon_enchantment_field.getSelectedItem().toString()
							.equals(Game_constants.FREEZING)) {
						item_weapon = new Freezing(item_weapon);
						item_model.setItem_weapon_enchantment_string(item_weapon.getEnchantment());
					}
					else if (item_view.item_weapon_enchantment_field.getSelectedItem().toString()
							.equals(Game_constants.BURNING)) {
						item_weapon = new Burning(item_weapon);
						item_model.setItem_weapon_enchantment_string(item_weapon.getEnchantment());
					}
					else if (item_view.item_weapon_enchantment_field.getSelectedItem().toString()
							.equals(Game_constants.SLAYING)) {
						item_weapon = new Slaying(item_weapon);
						item_model.setItem_weapon_enchantment_string(item_weapon.getEnchantment());
					}
					else if (item_view.item_weapon_enchantment_field.getSelectedItem().toString()
							.equals(Game_constants.FRIGHTENING)) {
						item_weapon = new Frightening(item_weapon);
						item_model.setItem_weapon_enchantment_string(item_weapon.getEnchantment());
					}
					else if (item_view.item_weapon_enchantment_field.getSelectedItem().toString()
							.equals(Game_constants.PACIFYING)) {
						item_weapon = new Pacifying(item_weapon);
						item_model.setItem_weapon_enchantment_string(item_weapon.getEnchantment());
					}
				} else
				{
					item_model.setItem_weapon_enchantment("");
					item_model.setItem_weapon_enchantment_string("");
				}

				item_model.save();
				new ManageItemController();
				item_view.dispose();
			} catch (NumberFormatException e) {
				JOptionPane.showOptionDialog(null, "Please Enter valid points between 1 and 5", "Invalid Points",
						JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[] {}, null);
			}
		} else if (arg0.getSource().equals(item_view.back_button)) {
			new ManageItemController();

			item_view.dispose();
		} else if (arg0.getSource().equals(item_view.update_item)) {
			if (item_view.item_name_field.getText().equals("")) {
				JOptionPane.showOptionDialog(null, "Please Enter Valid name", "Invalid Name",
						JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[] {}, null);
				return;
			}
			try {
				String item_name = item_view.item_name_field.getText();
				item_model.setItem_name(item_name);
				String item_type = (String) item_view.item_type_field.getSelectedItem();
				item_model.setItem_type(item_type);
				String item_ability = (String) item_view.item_ability_field.getSelectedItem();
				item_model.setItem_ability(item_ability);
				int item_point = Integer.parseInt(item_view.item_score_field.getText());
				item_model.setItem_point(item_point);
				
				

				if (item_point <= 0 || item_point >= 6) {

					throw new NumberFormatException();
				}
				
				Weapons item_weapon = new WeaponType(item_point);
				if ((item_type.equals(Game_constants.WEAPON_MELEE) || item_type.equals(Game_constants.WEAPON_RANGE))
						&& item_view.item_weapon_enchantment_field.getSelectedItem() != null)
				{
					
					item_model.setItem_weapon_enchantment(
							item_view.item_weapon_enchantment_field.getSelectedItem().toString());
					// code for weapon enchantment
					
					if (item_view.item_weapon_enchantment_field.getSelectedItem().toString()
							.equals(Game_constants.FREEZING)) {
						item_weapon = new Freezing(item_weapon);
						item_model.setItem_weapon_enchantment_string(item_weapon.getEnchantment());
					}
					else if (item_view.item_weapon_enchantment_field.getSelectedItem().toString()
							.equals(Game_constants.BURNING)) {
						item_weapon = new Burning(item_weapon);
						item_model.setItem_weapon_enchantment_string(item_weapon.getEnchantment());
					}
					else if (item_view.item_weapon_enchantment_field.getSelectedItem().toString()
							.equals(Game_constants.SLAYING)) {
						item_weapon = new Slaying(item_weapon);
						item_model.setItem_weapon_enchantment_string(item_weapon.getEnchantment());
					}
					else if (item_view.item_weapon_enchantment_field.getSelectedItem().toString()
							.equals(Game_constants.FRIGHTENING)) {
						item_weapon = new Frightening(item_weapon);
						item_model.setItem_weapon_enchantment_string(item_weapon.getEnchantment());
					}
					else if (item_view.item_weapon_enchantment_field.getSelectedItem().toString()
							.equals(Game_constants.PACIFYING)) {
						item_weapon = new Pacifying(item_weapon);
						item_model.setItem_weapon_enchantment_string(item_weapon.getEnchantment());
					}
				}
				else
				{
					item_model.setItem_weapon_enchantment("");
					item_model.setItem_weapon_enchantment_string("");
				}
				item_model.update();

				new ManageItemController();

				item_view.dispose();
			} catch (NumberFormatException e) {
				JOptionPane.showOptionDialog(null, "Please Enter valid points between 1 and 5", "Invalid Points",
						JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, new Object[] {}, null);
			}

		}

	}
}