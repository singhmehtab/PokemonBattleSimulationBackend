Pokemon Battle Simulation App Backend

Details

1. Pokemon data is fetched from pokeapi.co

2. Data has been fetched using Rest api GET call

3. Completable futures has been used to make api calls in parallel.
4. Pokemon data has been saved in the memory of backend server using ehcache.
5. Pokemon data can be fetched using exposed rest api.
6. Battle simulation can be done using websockets.
7. Server listens on an endpoint to initialize battle and produces data on a different topic for the client