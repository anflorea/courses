#include "master_cpp.h"
#include "repository.h"
#include "controller.h"
#include "ui.h"

int		main(void)
{
	Repository	repo = Repository();
	Controller	cont = Controller(repo);
	Ui			ui = Ui(cont);
	string		cmd;

	system("clear");
	while (1) {
		cout << "\n\n\n\tEnter 1 for administrator mode and 2 for user mode :) ";
		cin >> cmd;
		if (cmd == "1") {
			while (cmd != "password") {
				cout << "Please enter the password for administrator mode: ";
				cin >> cmd;
			}
			ui.run();
		}
		else if (cmd == "2")
			cout << "\nUser mode is under construction. brb...\n";
		else
			cout << "\nCommand not found.\n";
	}
	return (0);
}
