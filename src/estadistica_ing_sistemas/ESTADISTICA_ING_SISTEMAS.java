
package estadistica_ing_sistemas;

import java.io.*;
import java.util.*;
import java.lang.Math;

public class ESTADISTICA_ING_SISTEMAS {
    public static void main(String[] args) throws FileNotFoundException{
        // es posible que se lanze una excepcion si nose encuentre el archivo especificado
        //Leer numeros del archivo
        List<Double> numeros = new ArrayList<>();
         File file = new File("C:\\numeros.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextDouble()) {
            numeros.add(scanner.nextDouble());
        }
        scanner.close();
        
        
        Scanner teclado = new Scanner(System.in);     
        
        // Calcular el rango
        double min = Collections.min(numeros);
        double max = Collections.max(numeros);
        double rango = max - min;
        
        //optiene la cantidad de elemnetos de la lista "numeros"
        int n = numeros.size();
        
        System.out.println("Elige una opcion: ");
        System.out.println("1.De 5 en 5");
        System.out.println("2. clases=1 + log2(n)");
        System.out.println("3. Raiz de n");
        System.out.println("4. MAnual");
        int op=teclado.nextInt();
        
        
        //Variables globales
        int numClases = 0, ir = 0;
        double numC, intervalo = 0;
        
        if(op==1){
          
                    //Determinar el numero de clases DE 5 EN 5
                    //interbalo == separacion de cada clase ej [11,20)intervalo = 9
                    numClases=5;
                    intervalo= rango/numClases;
                    ir=(int)Math.ceil(intervalo);// para q el valor sea entero
        }else if(op==2){
                  //Determinar el numero de clases (1+log2)*(n))
                  numC =  (1 + Math.log(n) / Math.log(2));
                  numClases=(int) Math.ceil(numC);
                  intervalo= rango/numClases;
                  ir=(int)Math.ceil(intervalo);
            
        }else if(op==3){
                //determinar el numero de clases (Raiz de n)
               numC= Math.sqrt(n);
               numClases=(int)Math.ceil(numC);
               intervalo= rango/numClases;
               ir=(int)Math.ceil(intervalo);
        
        } else if(op==4){
            System.out.println("ELige el numero de clases");
            numClases=teclado.nextInt();
            intervalo= rango/numClases;
            ir=(int)Math.ceil(intervalo);
        
        }else{
          System.out.println("Opcion no valida: ");
          System.exit(0);
        }
 
        // Calcular los intervalos de clase y las marcas de clase
        double[] Li_1 = new double[numClases];//lim. superiores y lim. inferiores
        double[] Li = new double[numClases];// medio
        double[] Xi = new double[numClases];//de cada clase (son bloques) 
        
        for (int i = 0; i < numClases; i++) {
            Li_1[i] = min + i * ir;
            Li[i] = Li_1[i] + ir;
            Xi[i] = (Li_1[i] + Li[i]) / 2;// se calcula lim sup y inf
            
        }
        
        //frecuencia absoluta (ni) de cada clase
        int[] ni = new int[numClases];
        for (double num : numeros) {
            for (int i = 0; i < numClases; i++) {
                if (num >= Li_1[i] && num < Li[i]) {
                    ni[i]++;
                    break;
                }
            }
        }
        //Frecuencia absoluta acumulada Ni 
        double[] Ni=new double[numClases];
        
        Ni[0] = ni[0];
        for (int i = 1; i < numClases; i++) {
            Ni[i] = Ni[i-1] + ni[i];
        }

        //hi calcular en porcentajes
        double[] hi = new double[numClases];
        for (int i=0; i<numClases; i++){
            hi[i]=(double)ni[i]/n*100;
        }
        
        //HI frecuencia absoluta acumulada
        double[] Hi = new double[numClases];
        Hi[0] = hi[0];
        for (int i = 1; i < numClases; i++) {
            Hi[i] = Hi[i-1] + hi[i];
        }

        // Imprimir los resultados
        System.out.println("Maximo: " + max);
        System.out.println("Minimo: " + min);
        System.out.println("Rango: " + rango);
        System.out.println("El tamanio del intervalo es (sin redondear): " + intervalo);
        System.out.println("El tamanio del intervalo es: " + ir );
        System.out.println("|N.clases|\tLi-1-Li| \tXi\tni\tNi\thi\tHi");
        
        for (int i = 0; i < numClases; i++) {
            System.out.printf("%d\t\t[%.2f\t%.2f)\t%.2f\t%d\t%.2f\t%.2f\t%.2f", i+1, Li_1[i], 
                    Li[i], Xi[i], ni[i],Ni[i], hi[i], Hi[i]);
        System.out.println("");
        }
    }
}
