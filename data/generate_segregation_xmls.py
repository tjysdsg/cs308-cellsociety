import random

from xml_generator import generate_file

if __name__ == "__main__":
  cell_r = []
  cell_c = []
  cell_s = []
  for i in range(100):
    for j in range(100):
      cell_r.append(i)
      cell_c.append(j)
      cell_s.append(
          random.choice([0, 0, 0, 0, 0, 1, 1, 1, 2])
      )

  generate_file(
      f"SegregationExample.xml",
      "Segregation", 100, 100,
      cell_r, cell_c, cell_s,
      speed=20, title=f"Segregation Example", author='jt304',
      desc=f'An interesting example of Segregation',
      sim_configs=dict(threshold=0.4),
  )
