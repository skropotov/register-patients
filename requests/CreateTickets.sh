curl --location --request POST 'http://localhost:9000/ws' \
--header 'Content-Type: text/xml; charset=utf-8' \
--data-raw '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
				  xmlns:gs="http://skropotov.ru/ws/ticket-service">
   <soapenv:Header/>
   <soapenv:Body>
      <gs:createTicketsRequest>
         <gs:startHour>10</gs:startHour>
         <gs:endHour>18</gs:endHour>
         <gs:startDate>2022-08-29</gs:startDate>
         <gs:endDate>2022-09-04</gs:endDate>
         <gs:visitDuration>1</gs:visitDuration>
      </gs:createTicketsRequest>
   </soapenv:Body>
</soapenv:Envelope>'