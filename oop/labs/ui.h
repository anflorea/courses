#ifndef FT_UI_H
# define FT_UI_H

# include "master_cpp.h"
# include "controller.h"

class	Ui
{
	private:
		Controller	ctrl;

	public:
		Ui(const Controller& controller): ctrl(controller) {}
		void	printMenu(void);
		void	run(void);
		void	printList(vector<Tutorial> list);
		string	readTitle(void);
		string	readPresenter(void);
		long	readLikes(void);
		string	readLink(void);
		long	readDuration(void);
		void	init_repo(void);
};

#endif
