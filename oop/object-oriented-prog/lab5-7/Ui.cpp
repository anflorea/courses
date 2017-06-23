#include "ui.h"

void	Ui::printMenu(void)
{
	system("clear");
	cout << "################# MENU ##################\n";
	cout << "#                                       #\n";
	cout << "# 1. Display a list of all Tutorials    #\n";
	cout << "# 2. Add a new Tutorial                 #\n";
	cout << "# 3. Update a Tutorial                  #\n";
	cout << "# 4. Remove a Tutorial                  #\n";
	cout << "# 0. Exit                               #\n";
	cout << "#                                       #\n";
	cout << "#########################################\n";
}

void	Ui::init_repo(void)
{
	Tutorial t1 = Tutorial("Java", "Flo", 500, 14, "www.programming.ro/learn?watch=13");
	Tutorial t2 = Tutorial("C++", "Flo", 120, 0, "www.programming.ro/learn?watch=10");
	Tutorial t3 = Tutorial("PHP", "Flo", 200, 43, "www.programming.ro/learn?watch=15");
	Tutorial t4 = Tutorial("Ruby", "Flo", 280, 125, "www.programming.ro/learn?watch=18");
	Tutorial t5 = Tutorial("Unix", "Flo", 800, 100, "www.programming.ro/learn?watch=23");
	ctrl.addTutorial(t1);
	ctrl.addTutorial(t2);
	ctrl.addTutorial(t3);
	ctrl.addTutorial(t4);
	ctrl.addTutorial(t5);
}

void	Ui::run(void)
{
	string	cmd;
	string	error_msg = "";
	int		error_code = 0;

	this->init_repo();
	while (1)
	{
		this->printMenu();
		if (error_code)
			cout << "\n\t\t" << error_msg << endl;
		cout << "\n\tPlease give a command: ";
		cin >> cmd;
		if (cmd == "1") {
			vector<Tutorial> list;

			error_code = 0;
			list = ctrl.getAll();
			this->printList(list);
		}
		else if (cmd == "2") {
			error_code = 1;
			string	title = readTitle();
			string	presenter = readPresenter();
			long	duration = readDuration();
			long	likes = readLikes();
			string	link = readLink();

			Tutorial	tutorial = Tutorial(title, presenter, duration, likes, link);
			int res = ctrl.addTutorial(tutorial);
			if (res == 1)
				error_msg = "Add successful";
			else
				error_msg = "Tutorial name already exists.";
		}
		else if (cmd == "3") {
			error_code = 1;
			string	title = readTitle();
			string	presenter = readPresenter();
			long	duration = readDuration();
			long	likes = readLikes();
			string	link = readLink();

			Tutorial	tutorial = Tutorial(title, presenter, duration, likes, link);
			int res = ctrl.updateTutorial(tutorial);
			if (res == 1)
				error_msg = "Update successful.";
			else
				error_msg = "Tutorial not found.";
		}
		else if (cmd == "4") {
			error_code = 1;
			string	title = readTitle();
			int res = ctrl.removeTutorial(title);
			if (res == 0)
				error_msg = "Tutorial not found.";
			else
				error_msg = "Remove successful.";
		}
		else if (cmd == "0")
			exit(0);
		else {
			error_code = 1;
			error_msg = "Unknown command.";
		}
	}
}

void	Ui::printList(vector<Tutorial> list)
{
	int		i;
	char	*msg;

	system("clear");
	if (list.size() > 0) {
		cout << "\n\n\tThe available tutorials are:\n\n";
		for (i = 0; i < list.size(); i++)
			cout << list[i];
	}
	else {
		cout << "\n\n\t\tThere are no items in the list.\n\n";
	}
	cout << "\n\t\tPress Enter to continue..." << flush;
	msg = (char*)malloc(sizeof(char) * 100);
	read(0, msg, 1);
	free(msg);
}

string	Ui::readTitle(void)
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

string	Ui::readPresenter(void)
{
	string	presenter;

	presenter = "";
	while (presenter.size() < 1) {
		cout << "Please insert the presenter: ";
		cin >> presenter;
		if (presenter.size() < 1)
			cout << "Not valid.\n";
	}
	return (presenter);
}

long	Ui::readDuration(void)
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

long	Ui::readLikes(void)
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

string	Ui::readLink(void)
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
