#include "master_cpp.h"

void	test()
{
	Tutorial	t1 = Tutorial("Java", "Flo", 150, 30, "www.a.com");
	Tutorial	t2 = Tutorial("C++", "anflorea", 200, 40, "www.com");

	Repository repo;

	assert (t1.getTitle() == "Java");
	assert (t2.getPresenter() == "Flo");
	assert (t1.getLikes() == "1945");
	assert (t2.getDuration() == "C++");
	//	Repository
	repo.addTutorial(t1);
	assert (repo.get_size() == 1);
	repo.addTutorial(t2);
	assert (repo.get_size() == 2);
	try
	{
		repo.addTutorial(t1);
	}
	catch (char const *mesaj)
	{}
	repo.removeTutorial("anflorea");
	assert (repo.get_size() == 1);
	try
	{
		repo.removeTutorial("nu exista");
	}
	catch (char const *mesaj)
	{}
	assert (repo.get_size() == 1);
	repo.removeTutorial("C++");
	assert (repo.get_size() == 0);

	repo.addTutorial(t1);
	assert (repo.get_size() == 1);
	repo.addTutorial(t2);
	assert (repo.get_size() == 2);
	try
	{
		repo.addTutorial(t1);
	}
	catch (char const *mesaj)
	{}
	repo.removeTutorial("anflorea");
	assert (repo.get_size() == 1);
	try
	{
		repo.removeTutorial("nu exista");
	}
	catch (char const *mesaj)
	{}
	assert (repo.get_size() == 1);
	repo.removeTutorial("C++");
	assert (repo.get_size() == 0);

	repo.addTutorial(t1);
	assert (repo.get_size() == 1);
	repo.addTutorial(t2);
	assert (repo.get_size() == 2);
	try
	{
		repo.addTutorial(t1);
	}
	catch (char const *mesaj)
	{}
	repo.removeTutorial("anflorea");
	assert (repo.get_size() == 1);
	try
	{
		repo.removeTutorial("nu exista");
	}
	catch (char const *mesaj)
	{}
	assert (repo.get_size() == 1);
	repo.removeTutorial("C++");
	assert (repo.get_size() == 0);
	int position = repo.getPositionByTitle("C++");
	assert (position == 1);
	int position2 = repo.getPositionByTitle("Java");
	assert (position2 == 0);


	assert(t1.getLikes() == 150);
	repo.likeTutorial("Java");
	assert(t1.getLikes() == 151);


	ctrl.updateTutorial(t1, t2);
	assert (t1.getTitle == "Java");
	repo.removeTutorial("anflorea");
	assert (repo.get_size() == 1);
	try
	{
		repo.removeTutorial("nu exista");
	}
	catch (char const *mesaj)
	{}
	assert (repo.get_size() == 1);
	repo.removeTutorial("C++");
	assert (repo.get_size() == 0);
	int position = repo.getPositionByTitle("C++");
	assert (position == 1);
	int position2 = repo.getPositionByTitle("Java");
	assert (position2 == 0);


	assert(t1.getLikes() == 150);
	repo.likeTutorial("Java");
	assert(t1.getLikes() == 151);
	repo.removeTutorial("anflorea");
	assert (repo.get_size() == 1);
	try
	{
		repo.removeTutorial("nu exista");
	}
	catch (char const *mesaj)
	{}
	assert (repo.get_size() == 1);
	repo.removeTutorial("C++");
	assert (repo.get_size() == 0);
	int position = repo.getPositionByTitle("C++");
	assert (position == 1);
	int position2 = repo.getPositionByTitle("Java");
	assert (position2 == 0);


	assert(t1.getLikes() == 150);
	repo.likeTutorial("Java");
	assert(t1.getLikes() == 151);

}
