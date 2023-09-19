# Smart4Aviation

I created a set of 2 APIs (plus a couple of test APIs to retrieve the basic data stored inside the json files).
Here follows the examples of cURL for the two APIs:

1. For requested Flight Number and date will respond with following :
   a. Cargo Weight for requested Flight
   b. Baggage Weight for requested Flight
   c. Total Weight for requested Flight

   curl --location 'localhost:8080/api/flights/1154/date/2018-08-25T01%3A52%3A07-02%3A00'

2. For requested IATA Airport Code and date will respond with following :
   a. Number of flights departing from this airport,
   b. Number of flights arriving to this airport,
   c. Total number (pieces) of baggage arriving to this airport,
   d. Total number (pieces) of baggage departing from this airport.

   curl --location 'localhost:8080/api/airports/ANC/date/2022-03-14T01%3A24%3A59-01%3A00'




