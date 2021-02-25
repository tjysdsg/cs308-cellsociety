from typing import List


class Tag:
  def __init__(self, tag_name: str, **kwargs):
    self.tag_name = tag_name
    self.attrs = {}
    self.children = []

    for k, v in kwargs.items():
      self.set_tag_attr(k, v)

  def set_tag_attr(self, attr_name: str, attr_val: str):
    self.attrs[attr_name] = attr_val

  def add_child(self, child):
    self.children.append(child)

  def add_children(self, children: list):
    self.children += children

  def __str__(self):
    ret = f"<{self.tag_name}"
    for k, v in self.attrs.items():
      ret += f' {k}="{v}"'
    ret += ">\n"

    for c in self.children:
      ret += str(c) + '\n'

    ret += f"</{self.tag_name}>"
    return ret


xml_base = '<?xml version="1.0" encoding="UTF-8" ?>\n'


def make_simple_tag(tag_name: str, value) -> Tag:
  ret = Tag(tag_name)
  ret.add_child(value)
  return ret


def make_cell(r, c, s) -> Tag:
  ret = Tag("cell")

  row = make_simple_tag("row", r)
  col = make_simple_tag("col", c)
  state = make_simple_tag("state", s)

  ret.add_children([row, col, state])
  return ret


def make_grid(n_rows, n_cols) -> Tag:
  ret = Tag("grid")

  sizex = make_simple_tag("sizex", n_rows)
  sizey = make_simple_tag("sizey", n_cols)

  ret.add_children([sizex, sizey])
  return ret


def generate_file(
    filename: str,
    simulation_type: str,
    n_rows: int,
    n_cols: int,
    cell_r: List[int],
    cell_c: List[int],
    cell_s: List[int],
    speed: int,
    title: str,
    author: str,
    desc: str,
    sim_configs=None,
    cell_init_type: str = 'locationonly',
):
  if sim_configs is None:
    sim_configs = {}

  data = Tag("data", simulation=simulation_type)

  # cell init type
  cell_init = Tag('cellinit')
  cell_init.add_child(cell_init_type)
  data.add_child(cell_init)

  # grid
  grid = make_grid(n_rows, n_cols)
  data.add_child(grid)

  # cells
  n_cells = len(cell_r)
  data.add_children([
    make_cell(cell_r[i], cell_c[i], cell_s[i]) for i in range(n_cells)
  ])

  data.add_children([
    make_simple_tag("speed", speed),
    make_simple_tag("title", title),
    make_simple_tag("author", author),
    make_simple_tag("description", desc),
  ])

  data.add_children(
      [make_simple_tag(k, v) for k, v in sim_configs.items()]
  )

  xml_str = xml_base + str(data)

  with open(filename, 'w') as f:
    f.write(xml_str)
