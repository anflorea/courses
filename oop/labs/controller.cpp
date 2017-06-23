#include "controller.h"

int		Controller::addTutorial(Tutorial tutorial)
{
	Tutorial	*t;

	t = repo.getOneByTitle(tutorial.getTitle());
	if (t != NULL)
		return (0);
	repo.addTutorial(tutorial);
	return (1);
}

int		Controller::addToWatchList(Tutorial tutorial)
{
	int		pos;

	pos = repo.getWatchPositionByTitle(tutorial.getTitle());
	if (pos != -1)
		return (0);
	repo.addToWatchList(tutorial);
	return (1);
}

void	Controller::likeTutorial(string title)
{
	repo.likeTutorial(title);
	repo.storeToFile();
}

int		Controller::removeTutorial(string title)
{
	int		position;

	position = repo.getPositionByTitle(title);
	if (position == -1)
		return (0);
	repo.removeTutorial(position);
	return (1);
}

int		Controller::removeFromWatchList(string title)
{
	int		position;

	position = repo.getWatchPositionByTitle(title);
	if (position == -1)
		return (0);
	repo.removeFromWatchList(position);
	return (1);
}

int		Controller::updateTutorial(Tutorial tutorial)
{
	int		position;

	position = repo.getPositionByTitle(tutorial.getTitle());
	if (position == -1)
		return (0);
	repo.updateTutorial(position, tutorial);
	return (1);
}

vector<Tutorial>	Controller::getAll(void)
{
	return (repo.getAll());
}

vector<Tutorial>	Controller::getWatchList(void)
{
	return (repo.getWatchList());
}

vector<Tutorial>	Controller::getByPresenter(string presenter)
{
	vector<Tutorial>	list;
	vector<Tutorial>	newList;
	int					i;

	list = repo.getAll();
	i = 0;
	while (i < list.size())
	{
		int a = list[i].getPresenter().find(presenter, 0);
		if (a >= 0 && a < list[i].getPresenter().size())
			newList.push_back(list[i]);
		i++;
	}
	return (newList);
}

Tutorial	*Controller::getOneByTitle(string title)
{
	return (repo.getOneByTitle(title));
}

void		Controller::storeToFile(void)
{
	repo.storeToFile();
}

void		Controller::storeWatchToFile(void)
{
	repo.storeWatchToFile();
}

void		Controller::getFromFile(void)
{
	repo.getFromFile();
}

void		Controller::getWatchFromFile(void)
{
	repo.getWatchFromFile();
}

void		Controller::openWatchOnBrowser(void)
{
	repo.openWatchOnBrowser();
}
