#include "repository.h"

Repository::Repository(void)
{
	tutorialList = vector<Tutorial>();
}

void	Repository::addTutorial(Tutorial tutorial)
{
	this->tutorialList.push_back(tutorial);
}

vector<Tutorial>	Repository::getAll(void)
{
	return (this->tutorialList);
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
