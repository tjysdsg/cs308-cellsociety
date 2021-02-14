from xml_generator import generate_file

if __name__ == "__main__":
    generate_file("WaTor_Simulation2.xml",
        "WaTor", 10, 15,
        [1,2,3,4,5,6,7,8,9,9,9,9,9,9,9,9,9], [14, 14,14,14,14,14,14,14,14,0,3,4,5,6,7,9,10], [1, 2,2,2,1,2,2,2,1,1,1,1,2,2,1,1,1],
        speed=20, title="WaTor simulation2", author='jw542', desc='Wator simulation 2 edge case',
        sim_configs=dict(fishBreedDuration=2, sharkBreedDuration=4, sharkStarveDuration =5),
    )
