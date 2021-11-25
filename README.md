# PPKWU-Lab4   

## GET /convert/analyze/{text}/{indirectFormat}/{returnFormat}  
Description: endpoint responsible for getting information about number of uppercase, lowercase, numbers, special characters and if contains its combination in passed string. Additionally it allows to indicate intermediate format of conversion (JSON, XML, CSV, TXT) and format of response (JSON, XML, CSV, TXT)

Path: /convert/analyze/{text}/{indirectFormat}/{returnFormat} (GET)  
Params:  
 {text} - passed string  
 {indirectFormat} - intermediate format of conversion (JSON, XML, CSV, TXT)  
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
/convert/analyze/aaAA12@$/JSON/TXT

Response:  
```
2  
2  
2  
2  
true  
```

## POST convert/{inFormat}/{outFormat}  
Description: endpoint responsible for converting passed text from one format to another

Path: convert/{inFormat}/{outFormat} (POST)  
Params:  
 {inFormat} - input format (JSON, XML, CSV, TXT)  
 {outFormat} - output format (JSON, XML, CSV, TXT)  
Body:   
 {text} - passed string  
Return: object with converted string  
Error message on passing incorrect format: Incorrect format  

EXAMPLES:  

convert CSV to JSON  
path: convert/CSV/JSON  
body:  
```
{
    "text": "uppercase,lowercase,number,specialChars,combination
             2,2,2,2,true"
}
```  

Response:  
```
{
    "specialChars": "2",
    "upperCase": "2",
    "lowerCase": "2",
    "numbers": "2",
    "combination": "true"
}
```

convert XML to TXT  
path: convert/XML/TXT  
body:   
```
{
    "text": "<analyze><uppercase>2</uppercase><lowercase>2</lowercase><specialChars>2</specialChars><numbers>2</numbers><combination>true</combination></analyze>"
}
```

Response:  
```
2
2
2
2
true
```
