# PPKWU-Lab4   

## GET /convert/analyze/{text}/{indirectFormat}/{returnFormat}  
Description: endpoint responsible for getting information about number of uppercase, lowercase, numbers, special characters and if contains its combination in passed string. Additionally it allows to indicate indirect format of conversion (JSON, XML, CSV, TXT) and type of response (JSON, XML, CSV, TXT)

Path: /convert/analyze/{text}/{indirectFormat}/{returnFormat} (GET)  
Params:  
 {text} - passed string  
 {indirectFormat} - indirect format of conversion (JSON, XML, CSV, TXT)  
 {returnFormat} - return format (JSON, XML, CSV, TXT)  
Return: object with data about passed string in passed format   
Error message on passing incorrect format: Incorrect format

EXAMPLES:

input for JSON reponse:  
/convert/analyze/aaAA12@$/XML/JSON  

Response:  
```
{
    "uppercase": 2,
    "lowercase": 2,
    "numbers": 2,
    "specialChars": 2,
    "combination": true
}  
```

input for XML reponse:  
/convert/analyze/aaAA12@$/JSON/XML  

Response:  
```
<analyze>
    <uppercase>2</uppercase>
    <lowercase>2</lowercase>
    <specialChars>2</specialChars>
    <numbers>2</numbers>
    <combination>true</combination>
</analyze>
```

input for CSV reponse:  
/convert/analyze/aaAA12@$/TXT/CSV  

Response:  
```
uppercase,lowercase,number,specialChars,combination
2,2,2,2,true
```

input for TXT reponse:  
/convert/analyze/aaAA12@$/JSON/CSV

Response:  
```
2  
2  
2  
2  
true  
```

## GET convert/{text}/{inFormat}/{outFormat}  
Description: endpoint responsible for converting passed text from one format to another

Path: convert/{text}/{inFormat}/{outFormat} (GET)  
Params:  
 {text} - passed string  
 {inFormat} - input format (JSON, XML, CSV, TXT)  
 {outFormat} - output format (JSON, XML, CSV, TXT)  
Return: object with converted string  
Error message on passing incorrect format: Incorrect format  

EXAMPLES:  

convert JSON to XML  
convert/{
    "uppercase": 2,
    "lowercase": 2,
    "numbers": 2,
    "specialChars": 2,
    "combination": true
}/JSON/XML  

Response:  
```
<analyze>
    <uppercase>2</uppercase>
    <lowercase>2</lowercase>
    <specialChars>2</specialChars>
    <numbers>2</numbers>
    <combination>true</combination>
</analyze>
```

convert TXT to CSV  
convert/2  
2  
2  
2  
true/JSON/CSV  

Response:  
```
uppercase,lowercase,number,specialChars,combination
2,2,2,2,true
```
