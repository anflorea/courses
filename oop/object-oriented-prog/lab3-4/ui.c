#include "bakery.h"

void	print_menu(void)
{
	CLEAR_TERMINAL;
	ft_putendl("#===================MENU================#");
	ft_putendl("#                                       #");
	ft_putendl("# 1. Add a material                     #");
	ft_putendl("# 2. Update a material                  #");
	ft_putendl("# 3. Remove a material                  #");
	ft_putendl("# 4. Show all                           #");
	ft_putendl("# 5. Show all past the expiration date  #");
	ft_putendl("# 6. Show all from a supplier that      #");
	ft_putendl("#       are short in supply             #");
	ft_putendl("# 7. Undo                               #");
	ft_putendl("# 8. Redo                               #");
	ft_putendl("# 0. Exit                               #");
	ft_putendl("#                                       #");
	ft_putendl("#=======================================#");
}

void	ui(t_listt **list)
{
	char	*line;
	char	*error_msg;
	int		error_code;
	int		res;
	t_date	*current_date;
	int		c_day, c_month, c_year;
	t_listt	*undoList;
	t_listt	*redoList;
	int		can_redo = 0;
	int		can_undo = 1;

	error_code = 0;
	undoList = NULL;
	redoList = NULL;
	error_msg = (char*)malloc(sizeof(char) * 50);
	line = (char*)malloc(sizeof(char) * 35);
	CLEAR_TERMINAL;
	ft_putendl("\tPlease insert the current date:\n\n");
	c_day = read_day();
	c_month = read_month();
	c_year = read_year();
	current_date = date_constructor(c_day, c_month, c_year);
	while (1)
	{
		print_menu();
		if (error_code)
		{
			ft_putchar('\n');
			ft_putendl(error_msg);
			ft_putstr("\n\n");
		}
		ft_putstr("\n\tPlease insert a command: ");
		get_next_line(0, &line);
		if (ft_strcmp(line, "0") == 0)
			break;
		else if (ft_strcmp(line, "1") == 0)
		{
			t_material	*material;
			t_date		*date;
			char		*name = read_name();
			char		*supplier = read_supplier();
			int			quantity = read_quantity();
			int			day = read_day();
			int			month = read_month();
			int			year = read_year();

			date = date_constructor(day, month, year);
			material = material_constructor(name, supplier, quantity, date);
			append_dem_undo(&undoList, list);
			can_redo = 0;
			can_undo = 1;
			res = add_material(list, material);
			error_code = 1;
			if (res == 1)
				ft_strcpy(error_msg, "Add successful.");
			else
				ft_strcpy(error_msg, "Material already exists. Quantity updated.");
		}
		else if (ft_strcmp(line, "2") == 0)
		{
			t_material	*material;
			t_date		*date;
			char		*name = read_name();
			char		*supplier = read_supplier();
			int			quantity = read_quantity();
			int			day = read_day();
			int			month = read_month();
			int			year = read_year();

			date = date_constructor(day, month, year);
			material = material_constructor(name, supplier, quantity, date);
			append_dem_undo(&undoList, list);
			can_redo = 0;
			can_undo = 1;
			res = update_material(list, material);
			error_code = 1;
			if (res == 1)
				ft_strcpy(error_msg, "Update successful.");
			else
				ft_strcpy(error_msg, "Material not found.");
		}
		else if (ft_strcmp(line, "3") == 0)
		{
			char	*name = read_name();

			append_dem_undo(&undoList, list);
			can_redo = 0;
			can_undo = 1;
			res = remove_material(list, name);
			error_code = 1;
			if (res == 1)
				ft_strcpy(error_msg, "Remove successful.");
			else
				ft_strcpy(error_msg, "Material not found.");
		}
		else if (ft_strcmp(line, "4") == 0)
		{
			print_all_list(list);
			error_code = 0;
		}
		else if (ft_strcmp(line, "5") == 0)
		{
			t_listt	*newList;
			char	*name = read_name();

			newList = NULL;
			getSortedListByName(&newList, list, name, current_date);
			print_all_list(&newList);
			error_code = 0;
		}
		else if (ft_strcmp(line, "6") == 0)
		{
			t_listt	*newList;
			ft_putendl("\n\tPlease insert the supplier name and the maximim quantity that you want to be shown:\n");
			char	*supplier = read_supplier();
			int		quantity = read_quantity();

			newList = NULL;
			getSortedListBySupplier(&newList, list, supplier, quantity);
			print_all_list(&newList);
			error_code = 0;
		}
		else if (ft_strcmp(line, "7") == 0)
		{
			if (can_undo)
				res = compute_undo(&undoList, list, &redoList);
			can_redo = 1;
			error_code = 1;
			if (res == 0 && can_undo)
				ft_strcpy(error_msg, "Nothing to be done.");
			else
				ft_strcpy(error_msg, "Undo successful.");
			can_undo = 0;
		}
		else if (ft_strcmp(line, "8") == 0)
		{
			error_code = 1;
			if (can_redo == 1)
			{
				compute_redo(&undoList, list, &redoList);
				ft_strcpy(error_msg, "Redo Successful.");
				can_redo = 0;
			}
			else
				ft_strcpy(error_msg, "Not possible.");
		}
		else
		{
			ft_strcpy(error_msg, "Invalid command!");
			error_code = 1;
		}
	}
}

void	print_all_list(t_listt **list)
{
	t_listt	*aux;
	char	*line;

	line = (char*)malloc(sizeof(char) * 10);
	CLEAR_TERMINAL;
	aux = *list;
	if (!aux)
		ft_putendl("\n\n\tThere are no items in the list!\n\n");
	else
	{
		ft_putstr("\n\n");
		while (aux)
		{
			ft_putstr(print_material(aux->material));
			aux = aux->next;
		}
	}
	ft_putendl("\n\t\tPress Enter to continue.");
	get_next_line(0, &line);
	free(line);
}

char	*read_name()
{
	char	*name;

	name = (char*)malloc(sizeof(char) * 35);
	ft_putstr("Please insert the name: ");
	get_next_line(0, &name);
	return (name);
}

char	*read_supplier()
{
	char	*supplier;

	supplier = (char*)malloc(sizeof(char) * 35);
	ft_putstr("Please insert the supplier: ");
	get_next_line(0, &supplier);
	return (supplier);
}

int		read_quantity()
{
	int		quantity;
	char	*line;

	line = (char*)malloc(sizeof(char) * 35);
	ft_putstr("Please insert the quantity:  ");
	while (1)
	{
		get_next_line(0, &line);
		if (ft_isdigit(line[0]))
		{
			quantity = ft_atoi(line);
			if (quantity > 0)
			{
				free(line);
				return (quantity);
			}
		}
		ft_putendl("Please insert a valid value!");
	}
}

int		read_day()
{
	int		day;
	char	*line;

	line = (char*)malloc(sizeof(char) * 35);
	ft_putstr("Please insert the day: ");
	while (1)
	{
		get_next_line(0, &line);
		if (ft_isdigit(line[0]))
		{
			day = ft_atoi(line);
			if (day > 0 && day <= 31)
			{
				free(line);
				return (day);
			}
		}
		ft_putendl("Please insert a valid value!");
	}
}

int		read_month()
{
	int		month;
	char	*line;

	line = (char*)malloc(sizeof(char) * 35);
	ft_putstr("Please insert the month: ");
	while (1)
	{
		get_next_line(0, &line);
		if (ft_isdigit(line[0]))
		{
			month = ft_atoi(line);
			if (month > 0 && month <= 12)
			{
				free(line);
				return (month);
			}
		}
		ft_putendl("Please insert a valid value!");
	}
}

int		read_year()
{
	int		year;
	char	*line;

	line = (char*)malloc(sizeof(char) * 35);
	ft_putstr("Please insert the year: ");
	while (1)
	{
		get_next_line(0, &line);
		if (ft_isdigit(line[0]))
		{
			year = ft_atoi(line);
			if (year >= 1970 && year <= 2080)
			{
				free(line);
				return (year);
			}
		}
		ft_putendl("Please insert a valid value!");
	}
}
