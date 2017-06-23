#include "user_ui.h"

void	User_ui::printMenu(void)
{
	system("clear");
	cout << "################# MENU ##################\n";
	cout << "#                                       #\n";
	cout << "# 1. See tutorials from a presenter     #\n";
	cout << "# 2. See watch list                     #\n";
	cout << "# 3. Remove tutorial from watch list    #\n";
	cout << "# 4. Open the watch list in browser     #\n";
	cout << "# 0. Exit                               #\n";
	cout << "#                                       #\n";
	cout << "#########################################\n";
}

void	User_ui::run(void)
{
	string	cmd;
	string	error_msg = "";
	int		error_code = 0;

	ctrl.getFromFile();
	ctrl.getWatchFromFile();
	while (1)
	{
		this->printMenu();
		if (error_code)
			cout << "\n\t\t" << error_msg << endl;
		cout << "\n\tPlease give a command: ";
		cin >> cmd;
		if (cmd == "1") {
			string presenter = readPresenter();
			if (presenter.compare("*") == 0)
				presenter = "";
			vector<Tutorial> list = ctrl.getByPresenter(presenter);

			int		i = 0;
			string	cmd2;
			if (list.size() > 0) {
				cout << "Available commands: next/open/add/back/like" << endl;
				while (1)
				{
					cout << endl << list[i] << "\nPlease insert a command: ";
					cin >> cmd2;
					if (cmd2 == "next")
					{
						i++;
						if (i == list.size())
							i = 0;
					}
					else if (cmd2 == "add")
					{
						int	res = ctrl.addToWatchList(list[i]);
						if (res == 0)
							cout << "Tutorial already in watch list." << endl;
						else
						{
							ctrl.storeWatchToFile();
							cout << "Success." << endl;
						}
					}
					else if (cmd2 == "open")
					{
						char	*str = (char*)malloc(sizeof(char) * 200);
						strcpy(str, "/usr/bin/firefox ");
						system(strcat(str, list[i].getLink().c_str()));
					}
					else if (cmd2 == "like")
					{
						ctrl.likeTutorial(list[i].getTitle());
						list[i].setLikes(list[i].getLikes() + 1);
						ctrl.storeWatchToFile();
					}
					else if (cmd2 == "back")
						break;
					else
						cout << "Unknown command" << endl;
				}
				error_code = 0;
			}
			else
			{
				error_code = 1;
				error_msg = "Presenter not found.";
			}
		}
		else if (cmd == "2") {
			vector<Tutorial>	list;

			list = ctrl.getWatchList();
			error_code = 0;
			printList(list);
		}
		else if (cmd == "3")
		{
			string title = readTitle();

			int res = ctrl.removeFromWatchList(title);
			error_code = 1;
			if (res == 1)
				error_msg = "Tutorial removed.";
			else
				error_msg = "Tutorial not in watch list.";
		}
		else if (cmd == "4")
			ctrl.openWatchOnBrowser();
		else if (cmd == "0")
		{
			ctrl.storeWatchToFile();
			return;
		}
		else {
			error_code = 1;
			error_msg = "Unknown command.";
		}
	}
}

void	User_ui::printList(vector<Tutorial> list)
{
	int		i;
	char	*msg;

	system("clear");
	if (list.size() > 0) {
		cout << "\n\n\tThe watch list is:\n\n";
		for (i = 0; i < list.size(); i++)
			cout << list[i];
	}
	else {
		cout << "\n\n\t\tThere are no tutorials in the watch list.\n\n";
	}
	cout << "\n\t\tPress Enter to continue..." << flush;
	msg = (char*)malloc(sizeof(char) * 100);
	read(0, msg, 1);
	free(msg);
}

string	User_ui::readTitle(void)
{
	string title;

	title = "";
	while (title.size() < 1) {
		cout << "Please insert the title: ";
		cin >> title;
		if (title.size() < 1)
			cout << "Not valid.\n";
	}
	return (title);
}

string	User_ui::readPresenter(void)
{
	string	presenter;

	cout << "Please insert the presenter: ";
	cin >> presenter;
	return (presenter);
}

long	User_ui::readDuration(void)
{
	long	duration;
	string	str;

	duration = -1;
	while (duration < 1) {
		try {
			cout << "Please insert the duration (in secconds): ";
			cin >> str;
			duration = atoi(str.c_str());
			if (duration <= 0)
				throw 20;
		}
		catch (int e) {
			if (duration < 1)
				cout << "Not valid.\n";
			duration = -1;
		}
	}
	return (duration);
}

long	User_ui::readLikes(void)
{
	long	likes;
	string	str;

	likes = -1;
	while (likes < 0) {
		try {
			cout << "Please insert the number of likes: ";
			cin >> str;
			likes = atoi(str.c_str());
			if (likes < 0)
				throw 20;
		}
		catch (int e) {
			if (likes < 0)
				cout << "Not valid.\n";
		}
	}
	return (likes);
}

string	User_ui::readLink(void)
{
	string	link;

	link = "";
	while (link.size() < 1) {
		cout << "Please insert the link: ";
		cin >> link;
		if (link.size() < 1)
			cout << "Not valid.\n";
	}
	return (link);
}
