#ifndef WOLF_3D_H
# define WOLF_3D_H

# include <unistd.h>
# include <stdlib.h>
# include <stdio.h>
# include <sys/time.h>
# include <math.h>
# include <fcntl.h>
# include "libft.h"
# include "mlx.h"

# define WIDTH 1000
# define HEIGHT 600

typedef enum	e_map_type
{
	CLASIC,
	NOISE
}				t_map_type;

typedef struct	s_player
{
	double		position_x;
	double		position_y;
	double		direction_x;
	double		direction_y;
	double		plane_x;
	double		plane_y;
	double		time;
	double		old_time;
	int			fps;
	int			static_fps;
	double		static_time;
	int			move_front;
	int			move_back;
	int			rotate_left;
	int			rotate_right;
	int			flash_mode;
}				t_player;

typedef struct	s_env
{
	void		*window;
	void		*mlx;
	void		*img;
	char		*string;
	int			map_width;
	int			map_height;
	char		*map_name;
	char		**map;
	t_player	*player;
	t_map_type	map_type;
}				t_env;

typedef struct	s_iteration
{
	int			x;
	double		camera_x;
	double		ray_pos_x;
	double		ray_pos_y;
	double		ray_dir_x;
	double		ray_dir_y;
	int			map_x;
	int			map_y;
	double		side_dist_x;
	double		side_dist_y;
	double		delta_dist_x;
	double		delta_dist_y;
	double		perp_wall_dist;
	int			step_x;
	int			step_y;
	int			hit;
	int			side;
	int			line_height;
	int			draw_start;
	int			draw_end;
	int			color;
}				t_iteration;

int				ft_count_char(char *str, char c);
void			ft_init_iteration(t_iteration *it, t_env *env);
void			calculate_stuff(t_iteration *it);
void			perform_dda(t_iteration *it, t_env *env);
void			draw_line(t_iteration *it, t_env *env);
void			ft_expose_hook(t_env *env);
void			calculate_fps(t_env *env);
void			move_player(t_env *env);
int				loop_hook(t_env *env);
void			ft_rotate_player(t_env *env, double rot_speed);
int				key_pressed_hook(int keycode, t_env *env);
int				key_released_hook(int keycode, t_env *env);
void			ft_get_map_dimensions(char *filename, t_env *env);
void			ft_resolve_line(t_env *env, char *line, int i);
void			ft_read_map(char *filename, t_env *env);
void			ft_resolve_map(int argc, char **argv, t_env *env);
t_player		*ft_create_player(t_env *env);
int				ft_get_color(t_iteration *it, t_env *env);

#endif
