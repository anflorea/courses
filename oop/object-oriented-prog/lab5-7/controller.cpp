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

int		Controller::removeTutorial(string title)
{
	int		position;

	position = repo.getPositionByTitle(title);
	if (position == -1)
		return (0);
	repo.removeTutorial(position);
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

Tutorial	*Controller::getOneByTitle(string title)
{
	return (repo.getOneByTitle(title));
}
