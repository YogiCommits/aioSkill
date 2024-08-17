package org.scripts.august.scripts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class SkillUI {
    public aioSkill sAIO;
    private JPanel contentPane;
    public JFrame frame;
    public JComboBox<String> skillCB;
    public JComboBox<String> optionCB;
    public JCheckBox enableCheckbox;

    private JButton btnStartStop;
    private JButton btnSave;
    private JButton btnLoad;
    private boolean isRunning = false;
    private JLabel secondaryOptionLabel;
    private JComboBox<String> secondaryOptionCB;

    // New components
    private JLabel primaryWeaponLabel;
    private JTextField primaryWeaponField;
    private JLabel secondaryWeaponLabel;
    private JTextField secondaryWeaponField;
    private JLabel foodLabel;
    private JTextField foodField;
    private JLabel boostLabel;
    private JTextField boostField;
    private JLabel alchLabel;
    private JTextField alchField;
    private String skill;
    private String option;
    private String secondaryOption;
    private String boost;

    // Checkboxes
    private JCheckBox slayWorldBossCheckBox;
    private JCheckBox randomizeSkillCheckBox;
    private JCheckBox prestigeCheckBox;

    public SkillUI(aioSkill sAIO) {
        this.sAIO = sAIO;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame = new JFrame();
                frame.setResizable(false);
                frame.setTitle("autoAIO");
                frame.setBounds(100, 100, 300, 325);
                contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                frame.setContentPane(contentPane);
                contentPane.setLayout(null);
                frame.setLocationRelativeTo(sAIO.ctx.mouse.getComponent());
                initComponents();
                frame.setVisible(true);
            }
        });
    }

    public boolean isVisible() {
        return frame != null && frame.isVisible();
    }

    public void toFront() {
        if (frame != null) {
            frame.toFront();
            frame.requestFocus();
        }
    }

    public void setVisible(boolean visible) {
        if (frame != null) {
            frame.setVisible(visible);
        }
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);

        JLabel skillLabel = new JLabel("Skill");
        String[] skills = { "Thieving", "Woodcutting", "Mining", "Slayer", "Fishing", "Runecrafting", "Crafting",
                "Smithing",
                "Magic", };
        skillCB = new JComboBox<>(skills);
        skillCB.setSelectedIndex(0);

        JLabel optionLabel = new JLabel("Option");
        optionCB = new JComboBox<>();
        updateOptions(skillCB.getSelectedItem().toString(), optionCB);

        secondaryOptionLabel = new JLabel("Secondary Option");
        secondaryOptionLabel.setVisible(true);

        secondaryOptionCB = new JComboBox<>();
        secondaryOptionCB.setVisible(true);
        secondaryOptionCB.addItem("None");
        secondaryOptionCB.addItem("Donator Zone");
        secondaryOptionCB.addItem("Corrupt Zone");
        secondaryOptionCB.addItem("Regular Zone");

        primaryWeaponLabel = new JLabel("Primary Weapon");
        primaryWeaponField = new JTextField(10);
        primaryWeaponLabel.setVisible(false);
        primaryWeaponField.setVisible(false);

        secondaryWeaponLabel = new JLabel("Secondary Weapon");
        secondaryWeaponField = new JTextField(10);
        secondaryWeaponLabel.setVisible(false);
        secondaryWeaponField.setVisible(false);

        foodLabel = new JLabel("Food");
        foodField = new JTextField(10);
        foodLabel.setVisible(false);
        foodField.setVisible(false);

        boostLabel = new JLabel("Boost");
        boostField = new JTextField(10);
        boostLabel.setVisible(false);
        boostField.setVisible(false);

        alchLabel = new JLabel("Item to Alch");
        alchField = new JTextField(10);
        alchLabel.setVisible(false);
        alchField.setVisible(false);

        slayWorldBossCheckBox = new JCheckBox("Slay World Boss");
        randomizeSkillCheckBox = new JCheckBox("Randomize Skill");
        prestigeCheckBox = new JCheckBox("Prestige");

        skillCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedSkill = skillCB.getSelectedItem().toString();
                updateOptions(selectedSkill, optionCB);
                toggleSecondaryOptions(selectedSkill);
            }
        });

        optionCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (skillCB.getSelectedItem().toString().equals("Smithing")) {
                    toggleSecondaryOptions("Smithing");
                }
            }
        });

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(skillLabel)
                                .addComponent(optionLabel)
                                .addComponent(secondaryOptionLabel)
                                .addComponent(primaryWeaponLabel)
                                .addComponent(secondaryWeaponLabel)
                                .addComponent(foodLabel)
                                .addComponent(boostLabel)
                                .addComponent(alchLabel)
                                .addComponent(slayWorldBossCheckBox)
                                .addComponent(randomizeSkillCheckBox)
                                .addComponent(prestigeCheckBox)) // Add this line
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(skillCB)
                                .addComponent(optionCB)
                                .addComponent(secondaryOptionCB)
                                .addComponent(primaryWeaponField)
                                .addComponent(secondaryWeaponField)
                                .addComponent(foodField)
                                .addComponent(boostField)
                                .addComponent(alchField)));

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(skillLabel)
                                .addComponent(skillCB))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(optionLabel)
                                .addComponent(optionCB))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(secondaryOptionLabel)
                                .addComponent(secondaryOptionCB))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(primaryWeaponLabel)
                                .addComponent(primaryWeaponField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(secondaryWeaponLabel)
                                .addComponent(secondaryWeaponField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(foodLabel)
                                .addComponent(foodField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(boostLabel)
                                .addComponent(boostField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(alchLabel)
                                .addComponent(alchField))
                        .addComponent(slayWorldBossCheckBox)
                        .addComponent(randomizeSkillCheckBox)
                        .addComponent(prestigeCheckBox));

        btnStartStop = new JButton("Start");
        btnSave = new JButton("Save");
        btnLoad = new JButton("Load");

        btnStartStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onStartStop();
            }
        });

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onSaveSettings();
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onLoadSettings();
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnStartStop);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnLoad);

        contentPane.setLayout(new BorderLayout());
        contentPane.add(mainPanel, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateOptions(String skill, JComboBox<String> optionCB) {
        optionCB.removeAllItems();
        String[] options;

        switch (skill) {
            case "Thieving":
                options = new String[] { "Silk stall", "Spice stall", "Baker's stall", "Coin stall",
                        "Silver Stall", "Upgrade gem stall", "Fur stall", "Sapphire stall", "Emerald stall",
                        "Ruby Stall" };
                break;
            case "Woodcutting":
                options = new String[] { "Tree", "Oak", "Willow", "Maple", "Yew", "Best" };
                break;
            case "Mining":
                options = new String[] { "Copper Ore", "Iron Ore", "Coal", "Gold Ore", "Mithril Ore", "Runite Ore",
                        "Dragon Ore", "Oxi Ore", "Luminite Ore", "Rune Essence" };
                break;
            case "Slayer":
                options = new String[] { "Turael", "Vannaka", "Nieve", "Nixite", "Best" };
                break;
            case "Fishing":
                options = new String[] { "Small Net", "Lure", "Cage", "Harpoon", "Fish", "Best" };
                break;
            case "Runecrafting":
                options = new String[] { "Random" };
                break;
            case "Crafting":
                options = new String[] { "Uncut Sapphire", "Uncut Ruby", "Uncut Emerald", "Uncut Diamond",
                        "Uncut Dragonstone", "Uncut Onyx", "Coal", "Luminite Ore",
                        "Uncut Zenyte", "Best" };
                break;
            case "Magic":
                options = new String[] { "High Alchemy" };
                break;
            case "Smithing":
                options = new String[] { "Smith", "Smelt" };
                break;
            default:
                options = new String[] {};
        }

        for (String option : options) {
            optionCB.addItem(option);
        }
    }

    @SuppressWarnings("static-access")
    private void onStartStop() {
        if (isRunning) {
            onApplyNewSettings();
            sAIO.started = false;
            isRunning = false;
            btnStartStop.setText("Start");
            sAIO.status = "Stopped";
        } else {
            onApplyNewSettings();
            sAIO.started = true;
            isRunning = true;
            btnStartStop.setText("Stop");
            sAIO.status = "Started";
        }
    }

    public void onCloseGUI() {
        frame.dispose();
    }

    @SuppressWarnings("static-access")
    private void onApplyNewSettings() {
        skill = (String) skillCB.getSelectedItem();
        option = (String) optionCB.getSelectedItem();
        secondaryOption = (String) secondaryOptionCB.getSelectedItem();
        boost = (String) boostField.getText();
        sAIO.skill = skill;
        sAIO.boost = boost;
        sAIO.firstOption = option;
        sAIO.secondOption = secondaryOption;
        sAIO.slayWorldBoss = slayWorldBossCheckBox.isSelected();
        sAIO.randomizeSkill = randomizeSkillCheckBox.isSelected();
        sAIO.prestige = prestigeCheckBox.isSelected();

        if (skill.equals("Magic")) {
            String alchItem = alchField.getText();
            sAIO.alchItem = alchItem;
        }

        if (skill.equals("Slayer")) {
            String primaryWeapon = primaryWeaponField.getText();
            String secondaryWeapon = secondaryWeaponField.getText();

            if (primaryWeapon != null) {
                sAIO.primaryWeaponString = primaryWeapon;
            } else {
                sAIO.primaryWeaponString = "";
            }

            if (secondaryWeapon != null) {
                sAIO.secondaryWeaponString = secondaryWeapon;
            } else {
                sAIO.secondaryWeaponString = "";
            }

            if (secondaryOption.equals("Food")) {
                String food = foodField.getText();
                if (food != null) {
                    sAIO.foodString = food;
                } else {
                    sAIO.foodString = "";
                }
            } else {
                sAIO.foodString = "";
            }
        }
    }

    private void onSaveSettings() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("skill", (String) skillCB.getSelectedItem());
        jsonObject.put("option", (String) optionCB.getSelectedItem());
        jsonObject.put("secondaryOption", (String) secondaryOptionCB.getSelectedItem());
        jsonObject.put("boost", (String) boostField.getText());
        jsonObject.put("slayWorldBoss", slayWorldBossCheckBox.isSelected());
        jsonObject.put("randomizeSkill", randomizeSkillCheckBox.isSelected());
        jsonObject.put("prestige", prestigeCheckBox.isSelected());

        if (skillCB.getSelectedItem().equals("Slayer")) {
            jsonObject.put("primaryWeapon", primaryWeaponField.getText());
            jsonObject.put("secondaryWeapon", secondaryWeaponField.getText());

            if (secondaryOptionCB.getSelectedItem().equals("Food")) {
                jsonObject.put("food", foodField.getText());
            }
        }

        try (FileWriter file = new FileWriter("settings.json")) {
            file.write(jsonObject.toString(4));
            System.out.println("Settings saved to settings.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toggleSecondaryOptions(String skill) {
        secondaryOptionCB.removeAllItems();

        boolean isSmithing = skill.equals("Smithing");
        boolean isSlayer = skill.equals("Slayer");
        boolean isMagic = skill.equals("Magic");
        // boolean isWoodcutting = skill.equals("Woodcutting");
        // boolean isFishing = skill.equals("Fishing");
        // boolean isThieving = skill.equals("Thieving");
        // boolean isMining = skill.equals("Mining");

        primaryWeaponLabel.setVisible(isSlayer);
        primaryWeaponField.setVisible(isSlayer);
        secondaryWeaponLabel.setVisible(isSlayer);
        secondaryWeaponField.setVisible(isSlayer);
        boostField.setVisible(isSlayer);
        boostLabel.setVisible(isSlayer);
        foodLabel.setVisible(isSlayer);
        foodField.setVisible(isSlayer);
        alchLabel.setVisible(isMagic);
        alchField.setVisible(isMagic);

        if (isSmithing) {
            if (optionCB.getSelectedItem() != null && optionCB.getSelectedItem().toString().equals("Smelt")) {
                secondaryOptionCB.addItem("Runite Ore");
                secondaryOptionCB.addItem("Dragon Ore");
            } else if (optionCB.getSelectedItem() != null && optionCB.getSelectedItem().toString().equals("Smith")) {
                secondaryOptionCB.addItem("Rune Platelegs");
                secondaryOptionCB.addItem("Dragon Platelegs");
            }
        }
        if (isSlayer) {
            String[] secondaryOptions = { "Food", "Prayer", "None" };
            for (String option : secondaryOptions) {
                secondaryOptionCB.addItem(option);
            }
        } else {
            secondaryOptionCB.addItem("None");
            secondaryOptionCB.addItem("Donator Zone");
            secondaryOptionCB.addItem("Corrupt Zone");
            secondaryOptionCB.addItem("Regular Zone");

        }
    }

    private void onLoadSettings() throws JSONException {
        try (FileReader reader = new FileReader("settings.json")) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject jsonObject = new JSONObject(tokener);

            skillCB.setSelectedItem(jsonObject.getString("skill"));

            updateOptions(jsonObject.getString("skill"), optionCB);
            toggleSecondaryOptions(jsonObject.getString("skill"));
            optionCB.setSelectedItem(jsonObject.getString("option"));

            if (jsonObject.getString("skill").equals("Slayer")) {
                primaryWeaponField.setText(jsonObject.optString("primaryWeapon", ""));
                secondaryWeaponField.setText(jsonObject.optString("secondaryWeapon", ""));
                if (jsonObject.has("secondaryOption")) {
                    secondaryOptionCB.setSelectedItem(jsonObject.getString("secondaryOption"));
                }
                if (jsonObject.has("food")) {
                    foodField.setText(jsonObject.getString("food"));
                }
                if (jsonObject.has("boost")) {
                    boostField.setText(jsonObject.getString("boost"));
                }
            }
            slayWorldBossCheckBox.setSelected(jsonObject.optBoolean("slayWorldBoss", false));
            randomizeSkillCheckBox.setSelected(jsonObject.optBoolean("randomizeSkill", false));
            prestigeCheckBox.setSelected(jsonObject.optBoolean("prestige", false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
