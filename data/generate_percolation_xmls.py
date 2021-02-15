import random

from xml_generator import generate_file

if __name__ == "__main__":
  generate_file(
      "PercolationEdge0.xml",
      "Percolation", 18, 20,
      [0 for i in range(20)],
      [i for i in range(20)],
      [0, 0, 2, 0, 0,
       2, 0, 0, 2, 0,
       0, 2, 0, 0, 2,
       0, 0, 2, 0, 0],
      speed=20, title="Percolation edge 1", author='jt304',
      desc='Percolation edge 1'
  )

  generate_file(
      "PercolationEdge1.xml",
      "Percolation", 10, 15,
      [i for i in range(10)],
      [0 for i in range(10)],
      [0, 0, 2, 0, 0,
       2, 0, 0, 2, 0],
      speed=20, title="Percolation edge 2", author='jt304',
      desc='Percolation simulation 2'
  )

  for idx in range(4):
    cell_r = []
    cell_c = []
    cell_s = []
    for i in range(30):
      for j in range(30):
        cell_r.append(i)
        cell_c.append(j)
        cell_s.append(
            # blocked 2/5, open 1/2, percolated 1/10
            random.choice([0, 0, 0, 0, 1, 1, 1, 1, 1, 2])
        )

    generate_file(
        f"PercolationRandom{idx}.xml",
        "Percolation", 30, 30,
        cell_r, cell_c, cell_s,
        speed=20, title=f"Percolation random {idx + 1}", author='jt304',
        desc=f'Percolation simulation {idx + 1}'
    )
