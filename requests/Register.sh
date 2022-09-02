curl --location --request POST 'http://localhost:9000/tickets/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "ticketId": 8,
    "patientId": 3
}'