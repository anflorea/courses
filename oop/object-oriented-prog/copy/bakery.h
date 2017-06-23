#ifndef BAKERY_H
# define BAKERY_H

// ------- Includes --------

# include <stdio.h>
# include <string.h>
# include "libft.h"

// -------------------------


// ------- Defines --------

# define CLEAR_TERMINAL ft_putstr("\033[2J")

// ------------------------

// ------- Typedefs -------

typedef struct			s_date
{
	int					day;
	int					month;
	int					year;
}						t_date;

typedef struct			s_material
{
	char				*name;
	char				*supplier;
	int					quantity;
	t_date				*date;	
}						t_material;

typedef struct			s_listt
{
	struct s_listt		*next;
	t_material			*material;
}						t_listt;

// -----------------------

// ----------UI------------

void		ui(t_listt **list);
/*
 *	Reads the name
 *	Input: -
 *	Output: name (char*)
 */
char		*read_name();
/*
 *	Reads the supplier
 *	Input: -
 *	Output: supplier (char*)
 */
char		*read_supplier();
/*
 *	Reads the quantity
 *	Input: -
 *	Output: quantity (int)
 */
int			read_quantity();
/*
 *	Reads the day
 *	Input: -
 *	Output: day (int)
 */
int			read_day();
/*
 *	Reads the month
 *	Input: -
 *	Output: month (int)
 */
int			read_month();
/*
 *	Reads the year
 *	Input: -
 *	Output: year (int)
 */
int			read_year();
/*
 *	Prints all the elements in the list
 *	Input: -
 *	Output: -
 */
void		print_all_list(t_listt **list);

// ------------------------

// ----------Controller------------

/*
 *	Adds a new material to a list
 *	Input: list (pointer to the list), material (pointer to a material)
 *	Output: 1/0
 *		Postcondition: 1 on success, 0 otherwise
 */
int		add_material(t_listt **list, t_material *material);
/*
 *	Updates a material from a list
 *	Input: list (pointer to the list), material (pointer to a material)
 *	Output: 1/0
 *		Postcondition: 1 on success, 0 otherwise
 */
int		update_material(t_listt **list, t_material *material);
/*
 *	Removes material from a list
 *	Input: list (pointer to the list), name (char*)
 *	Output: 1/0
 *		Postcondition: 1 on success, 0 otherwise
 */
int		remove_material(t_listt **list, char *name);
/*
 *	Computes a list of the materials that contain a given string, that are past their expiration date (also sorted by name), and stores it in the newList
 *	Input: newList, list (pointer to lists), date (pointer to a date)
 *	Output: -
 */
t_listt	**getSortedListByName(t_listt **newList, t_listt **list, char *name, t_date *date);

// ------------------------

// ----------Repository------------

/*
 * Performs a push_back on a list
 */
void				list_push_back(t_listt **begin_list, t_material *material);
/*
 *	Creates a new list element
 */
t_material			*getElementByName(t_listt **begin_list, char *name);
/*
 *	Frees the memory from a material type structure
 */
void				free_offer_memory(t_material *material);
/*
 *	Removes one element from the list (by name)
 */
void				ft_lstdell(t_listt **alst, void (*del)(t_material *), char *name);

// ------------------------

// ----------Domain------------

/*
 *	Constructor for the date structure
 *	Input: day, month, year
 *		Precondition: all are ints
 *	Output: date
 *		Postcondition: Pointer to a date
 */
t_date		*date_constructor(int day, int month, int year);
/*
 *	Constructor for the material structure
 *	Input: name, supplier, quantity, date
 *		Precondition: all are ints, except date which is a pointer to a date structure
 *	Output: material
 *		Postcondition: Pointer to a material
 */
t_material	*material_constructor(char *name, char *supplier, int quantity, t_date *date);
/*
 *	toString method
 *	Input: material
 *	Output: A string representation of the structure
 */
char	*print_material(t_material *material);
/*
 *	Getter for name
 *	Input: material
 *	Output: name (char*)
 */
char	*get_name(t_material *material);
/*
 *	Getter for supplier
 *	Input: material
 *	Output: supplier (char*)
 */
char	*get_supplier(t_material *material);
/*
 *	Getter for quantity
 *	Input: material
 *	Output: quantity (int)
 */
int		get_quantity(t_material *material);
/*
 *	Getter for day
 *	Input: material
 *	Output: day (int)
 */
int		get_day(t_material *material);
/*
 *	Getter for month
 *	Input: material
 *	Output: month (int)
 */
int		get_month(t_material *material);
/*
 *	Getter for year
 *	Input: material
 *	Output: year (int)
 */
int		get_year(t_material *material);
/*
 *	Setter for name
 *	Input: name (char*)
 *	Output: -
 */
void	set_name(t_material *material, char *name);
/*
 *	Setter for supplier
 *	Input: supplier (char*)
 *	Output: -
 */
void	set_supplier(t_material *material, char *supplier);
/*
 *	Setter for quantity
 *	Input: quantity (int)
 *	Output: -
 */
void	set_quantity(t_material *material, int quantity);
/*
 *	Setter for day
 *	Input: day (int)
 *	Output: -
 */
void	set_day(t_material *material, int day);
/*
 *	Setter for month
 *	Input: month (int)
 *	Output: -
 */
void	set_month(t_material *material, int month);
/*
 *	Setter for year
 *	Input: year (int)
 *	Output: -
 */
void	set_year(t_material *material, int year);

// ------------------------

#endif
