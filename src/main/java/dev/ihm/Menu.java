package dev.ihm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.stereotype.Controller;

import dev.exception.PlatException;
import dev.ihm.options.IOptionMenu;

@Controller
public class Menu {

	private Map<Integer, IOptionMenu> actions;

    private String menu;
    private Scanner scanner;

    public Menu(Scanner scanner, List<IOptionMenu> options) {
        this.scanner = scanner;
        this.actions = buildActions(options);
    }
    
	public Map<Integer, IOptionMenu> buildActions(List<IOptionMenu> options) {
		Map<Integer, IOptionMenu> actions = new HashMap<Integer, IOptionMenu>();
		int compteur = 1;
		for (IOptionMenu opt : options) {

			actions.put(compteur, opt);
			compteur++;
		}

		return actions;
	}

    public void afficher() {

        boolean continuer = true;

        while (continuer) {

            System.out.println(getMenuTexte());

            int choix = this.scanner.nextInt();

            try {
                this.actions.get(choix).executer();
            } catch (PlatException e) {
                continuer = false;
                System.out.println(e.getMessage());
            }
        }
    }

    private String getMenuTexte() {
        if (menu == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("** Restaurant Console App **");
            sb.append("\n");
            this.actions.forEach((index, option) -> {
                sb.append(index);
                sb.append(". ");
                sb.append(option.getTitre());
                sb.append("\n");
            });
            this.menu = sb.toString();
        }
        return this.menu;
    }
}
