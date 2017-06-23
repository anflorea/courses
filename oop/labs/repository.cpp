#include "repository.h"
#include "Store.h"
#include "Html.h"

Repository::Repository(void)
{
	tutorialList = vector<Tutorial>();
}

void	Repository::addTutorial(Tutorial tutorial)
{
	this->tutorialList.push_back(tutorial);
}

void	Repository::addToWatchList(Tutorial tutorial)
{
	this->watchList.push_back(tutorial);
}

vector<Tutorial>	Repository::getAll(void)
{
	return (this->tutorialList);
}

vector<Tutorial>	Repository::getWatchList(void)
{
	return (this->watchList);
}


Tutorial	*Repository::getOneByTitle(string title)
{
	int		i;

	for (i = 0; i < tutorialList.size(); i++) {
		if (tutorialList[i].getTitle().compare(title) == 0) {
			return (&tutorialList[i]);
		}
	}
	return (NULL);
}

void		Repository::removeTutorial(int position)
{
	this->tutorialList.erase(tutorialList.begin() + position);
}

void		Repository::removeFromWatchList(int position)
{
	this->watchList.erase(watchList.begin() + position);
}

void		Repository::updateTutorial(int position, Tutorial tutorial)
{
	tutorialList[position].setPresenter(tutorial.getPresenter());
	tutorialList[position].setDuration(tutorial.getDuration());
	tutorialList[position].setLikes(tutorial.getLikes());
	tutorialList[position].setLink(tutorial.getLink());
}

int			Repository::getPositionByTitle(string title)
{
	int		i;

	for (i = 0; i < tutorialList.size(); i++)
	{
		if (tutorialList[i].getTitle().compare(title) == 0) {
			return (i);
		}
	}
	return (-1);
}

int			Repository::getWatchPositionByTitle(string title)
{
	int		i;

	for (i = 0; i < watchList.size(); i++)
	{
		if (watchList[i].getTitle().compare(title) == 0)
			return (i);
	}
	return (-1);
}

void		Repository::likeTutorial(string title)
{
	int		i;

	for (i = 0; i < tutorialList.size(); i++)
	{
		if (tutorialList[i].getTitle().compare(title) == 0)
			tutorialList[i].setLikes(tutorialList[i].getLikes() + 1);
	}
}

void		Repository::storeToFile(void)
{
	int		i;

	ofstream	myfile;
	myfile.open("tutorials.csv");
	for (i = 0; i < tutorialList.size(); i++)
	{
		Tutorial t = tutorialList[i];
		myfile << t.getTitle() << ";" << t.getPresenter() << ";" << t.getDuration() << ";" << t.getLikes() << ";" << t.getLink() << endl;
	}
	myfile.close();
}

void		Repository::storeWatchToFile(void)
{
	/*int		i;

	ofstream	myfile;
	myfile.open("watchlist.csv");
	for (i = 0; i < watchList.size(); i++)
	{
		Tutorial t = watchList[i];
		myfile << t.getTitle() << ";" << t.getPresenter() << ";" << t.getDuration() << ";" << t.getLikes() << ";" << t.getLink() << endl;
	}
	myfile.close();
	*/
	Store	store;
	Html	html;

	store.storeAll(watchList);
	html.storeAll(watchList);
}

vector<string> explode(string inputstring, string delimiter){
	vector<string> explodes;
	inputstring.append(delimiter);
	while(inputstring.find(delimiter)!=string::npos){
		explodes.push_back(inputstring.substr(0, inputstring.find(delimiter)));
		inputstring.erase(inputstring.begin(), inputstring.begin()+inputstring.find(delimiter)+delimiter.size());
	}
	return explodes;
}

void		Repository::getFromFile(void)
{
	string		line;
	Tutorial	t("", "", 0, 0, "");
	vector<string> result;

	ifstream	myfile;
	while (tutorialList.size() > 0)
		tutorialList.erase(tutorialList.begin());
	myfile.open("tutorials.csv");
	while (myfile >> line)
	{
		result = explode(line, ";");
		t.setTitle(result[0]);
		t.setPresenter(result[1]);
		t.setDuration(atol(result[2].c_str()));
		t.setLikes(atol(result[3].c_str()));
		t.setLink(result[4]);
		tutorialList.push_back(t);
	}
	myfile.close();
}

void		Repository::getWatchFromFile(void)
{
	string		line;
	Tutorial	t("", "", 0, 0, "");
	vector<string> result;

	ifstream	myfile;
	while (watchList.size() > 0)
		watchList.erase(watchList.begin());
	myfile.open("watchlist.csv");
	while (myfile >> line)
	{
		result = explode(line, ";");
		t.setTitle(result[0]);
		t.setPresenter(result[1]);
		t.setDuration(atol(result[2].c_str()));
		t.setLikes(atol(result[3].c_str()));
		t.setLink(result[4]);
		watchList.push_back(t);
	}
	myfile.close();
}

void		Repository::openWatchOnBrowser(void)
{
	system("/usr/bin/firefox /home/flo/object-oriented-prog/lab5-7/TutorialsHtml.html");
}
