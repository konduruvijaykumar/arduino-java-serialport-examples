#include <DHT.h>

DHT dht;
int var;

#define DHT22_PIN 2

void setup()
{
    Serial.begin(9600);
    Serial.println("DHT TEST PROGRAM ");
    Serial.print("LIBRARY VERSION: ");
    //Serial.println(DHT_LIB_VERSION);
    Serial.println();
    Serial.println("Type,\tstatus,\tHumidity (%),\tTemperature (C)");
}

void loop()
{
    // READ DATA
    Serial.print("DHT22, \t");
    /*int chk = dht.read22(DHT22_PIN);
    switch (chk)
    {
        case DHTLIB_OK: 
            Serial.print("OK,\t"); 
            break;
        case DHTLIB_ERROR_CHECKSUM: 
            Serial.print("Checksum error,\t"); 
            break;
        case DHTLIB_ERROR_TIMEOUT: 
            Serial.print("Time out error,\t"); 
            break;
        default: 
            Serial.print("Unknown error,\t"); 
            break;
    }*/
    var = 0;
    while(var < 10){
      Serial.print(dht.getHumidity(), 1);
      Serial.print(",\t");
      Serial.println(dht.getTemperature(), 1);
  
      delay(3000);
      var++;
    }
    exit(0);
    // DISPLAY DATA
    /*Serial.print(dht.getHumidity(), 1);
    Serial.print(",\t");
    Serial.println(dht.getTemperature(), 1);

    delay(3000);*/

}
