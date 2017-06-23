#ifndef UI_H
# define UI_H

// -------- Prototypes ----

void		ui(t_listt **list);
t_date		*date_constructor(int day, int month, int year);
t_material	*material_constructor(char *name, char *supplier, int quantity, t_date *date);
char		*read_name();
char		*read_supplier();
int			read_quantity();
int			read_day();
int			read_month();
int			read_year();

// ------------------------

#endif
