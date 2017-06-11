#define LED_RED           2
#define LED_GREEN         3
#define LED_YELLOW        4

unsigned char LR=0;
unsigned char LG=0;
unsigned char LY=0;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(LED_RED,   OUTPUT);
  pinMode(LED_GREEN, OUTPUT);
  pinMode(LED_YELLOW,  OUTPUT);
  
  digitalWrite(LED_RED,   LOW);
  digitalWrite(LED_GREEN, LOW);
  digitalWrite(LED_YELLOW,  LOW);
}

void loop() {
  // put your main code here, to run repeatedly:

}

void serialEvent() {
    int ind=0;
    char buff[11];  
    if(Serial.available()){
        delay(10);
        while(Serial.available()){
            char c = (char)Serial.read();
            if(ind<10){
              buff[ind++] = c;
            }
        }
        buff[ind]=0;
        
        if      (strcmp(buff, "red")==0)  { LR = 1-LR; digitalWrite(LED_RED, LR);
        }else if(strcmp(buff, "green")==0){ LG = 1-LG; digitalWrite(LED_GREEN, LG);
        }else if(strcmp(buff, "yellow")==0) { LY = 1-LY; digitalWrite(LED_YELLOW, LY);
        }
    }  
}
