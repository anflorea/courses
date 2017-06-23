#ifndef FT_DOMAIN_H
# define FT_DOMAIN_H

# include "master_cpp.h"

class	Tutorial
{
	private:
		string	title;
		string	presenter;
		long	duration;
		long	likes;
		string	link;

	public:
		Tutorial(string title, string presenter, long duration, long likes, string link);

		void	setTitle(string title);
		void	setPresenter(string presenter);
		void	setDuration(long duration);
		void	setLikes(long likes);
		void	setLink(string link);

		string	getTitle();
		string	getPresenter();
		long	getDuration();
		long	getLikes();
		string	getLink();

		friend ostream &operator<<(ostream &out, Tutorial &t);
};

#endif
