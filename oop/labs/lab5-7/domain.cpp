#include "domain.h"

Tutorial::Tutorial(string title, string presenter, long duration, long likes, string link)
{
	this->title = title;
	this->presenter = presenter;
	this->duration = duration;
	this->likes = likes;
	this->link = link;
}

void	Tutorial::setTitle(string title)
{
	this->title = title;
}

void	Tutorial::setPresenter(string presenter)
{
	this->presenter = presenter;
}

void	Tutorial::setDuration(long duration)
{
	this->duration = duration;
}

void	Tutorial::setLikes(long likes)
{
	this->likes = likes;
}

void	Tutorial::setLink(string link)
{
	this->link = link;
}

string	Tutorial::getTitle(void)
{
	return (this->title);
}

string	Tutorial::getPresenter(void)
{
	return (this->presenter);
}

long	Tutorial::getDuration(void)
{
	return (this->duration);
}

long	Tutorial::getLikes(void)
{
	return (this->likes);
}

string	Tutorial::getLink(void)
{
	return (this->link);
}

ostream	&operator<<(ostream &out, Tutorial &t)
{
	int		min;
	int		sec;

	min = t.duration / 60;
	sec = t.duration % 60;
	out << "Title: " << t.title << ", Presenter: " << t.presenter << ", Likes: "<< t.likes << ", Duration: " << min << ":" << sec << ", Link: " << t.link << endl;
	return (out);
}
