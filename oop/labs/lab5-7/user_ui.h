#ifndef FT_USER_UI_H
# define FT_USER_UI_H

# include "master_cpp.h"
# include "controller.h"

class	User_ui
{
	private:
		Controller		ctrl;

	public:
		User_ui(const Controller& cont): ctrl(cont) {}
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
