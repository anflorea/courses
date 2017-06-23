#include "master_cpp.h"
#include "repository.h"
#include "controller.h"
#include "ui.h"
#include "user_ui.h"

int		main(void)
{
	Repository		repo = Repository();
	Controller		cont = Controller(repo);
	Ui				ui = Ui(cont);
	User_ui			u_ui = User_ui(cont);
	string			cmd;

	system("clear");
	while (1) {
		cout << "\n\n\n\tEnter:\n\t\t1 for administrator mode\n\t\t2 for user mode\n\t\t0 to exit\n\t\t:) ";
		cin >> cmd;
		if (cmd == "1") {
			while (cmd != "password") {
				cout << "Please enter the password for administrator mode: ";
				cin >> cmd;
			}
			ui.run();
			system("clear");
		}
		else if (cmd == "2")
		{
			u_ui.run();
			system("clear");
		}
		else if (cmd == "0")
			exit (0);
		else
			cout << "\nCommand not found.\n";
	}
	return (0);
}
