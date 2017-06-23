#ifndef FT_REPOSITORY_H
# define FT_REPOSITORY_H

# include "master_cpp.h"
# include "domain.h"

class	Repository
{
	private:
		vector<Tutorial>	tutorialList;
		vector<Tutorial>	watchList;

	public:
		Repository();
		/*
		 *	Description: Adds a new tutorial to the database
		 */
		void	addTutorial(Tutorial tutorial);
		/*
		 *	Description: Adds a new tutorial to the watch list
		 */
		void	addToWatchList(Tutorial tutorial);
		/*
		 *	Description: Returns a list of all the tutorials
		 */
		vector<Tutorial>	getAll(void);
		vector<Tutorial>	getWatchList(void);
		Tutorial	*getOneByTitle(string title);
		/*
		 *	Description: Returns the position in the list of the tutorial with the given title
		 */
		int			getPositionByTitle(string title);
		/*
		 *	Description: Returns the position of the tutorial in the watch list
		 */
		int			getWatchPositionByTitle(string title);
		/*
		 *	Description: Removes a tutorial at the given position
		 */
		void	removeTutorial(int position);
		void	removeFromWatchList(int position);
		/*
		 *	Description: Updates a tutorial in the database
		 */
		void	updateTutorial(int position, Tutorial tutorial);
		/*
		 *	Description: Stores the current tutorial list in a CSV file
		 */
		void	storeToFile(void);
		/*
		 *	Description: Stores the current watch list in a CSV file
		 */
		void	storeWatchToFile(void);
		/*
		 *	Description: Reads the tutorials list from a CSV file
		 */
		void	getFromFile(void);
		/*
		 *	Description: Reads the watch list from a CSV file
		 */
		void	getWatchFromFile(void);
		/*
		 *	Description: Adds a like to the tutorial with a certain title
		 */
		void	likeTutorial(string title);
		void	openWatchOnBrowser(void);
};

#endif
