from xml_generator import generate_file

if __name__ == "__main__":
    generate_file("WaTor_Simulation1.xml",
        "WaTor", 10, 15,
        [1,2,3,4,5,6,7,8,9], [14, 14,14,14,14,14,14,14,14], [1, 2,1,1,1,2,2,2,1],
        speed=20, title="WaTor simulation1", author='jw542', desc='Wator simulation 1 edge case',
        sim_configs=dict(fishBreedDuration=1, sharkBreedDuration=5, sharkStarveDuration =3),
    )
