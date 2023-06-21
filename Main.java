package com.reu;
import java.util.Scanner;

public class Main {




    public static double[][] invert(double[][] a) {
        int n = a.length;
        double [][]x = new double[n][n];
        double [][]b = new double[n][n];
        int []index = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;

        gauss(a, index);

        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i] * b[index[i]][k];

        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }

    public static void gauss(double[][] a, int[] index) {
        int n = index.length;
        double[] c = new double[n];

        for (int i = 0; i < n; ++i)
            index[i] = i;

        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }

        int k = 0;
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }


            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];

                a[index[i]][j] = pj;

                for (int l = j + 1; l < n; ++l)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }


    public static void main(String[] args) {
        System.out.println();
        System.out.println("Program zaliczeniowy: ");
        System.out.println("Wersja: 1.0");
        System.out.println("Autor: Kacper Kozieł");
        System.out.println();
        Scanner scan = new Scanner(System.in);
        while (true) {

            System.out.println();
            System.out.println("==Rozmiar pierwszej macierzy== ");
            System.out.print("Podaj ilość wierszy: ");
            scan.nextInt();
            int m;
            System.out.print("Podaj ilość kolumn: ");
            int n = scan.nextInt();
            System.out.println();

            System.out.println("==Rozmiar  drugiej macierzy==");
            System.out.print("Podaj ilość wierszy: ");
            int k = scan.nextInt();
            System.out.print("Podaj ilość kolumn: ");
            int j = scan.nextInt();

            m = j;
            System.out.println();
            System.out.println("Podaj zakres liczb: ");
            System.out.print("X: ");
            double x = scan.nextDouble();
            System.out.print("Y: ");
            double y = scan.nextDouble();


            double[][] tab1 = new double[m][n];
            double[][] tab2 = new double[k][j];


            System.out.println();
            System.out.println("===[Macierz pierwsza]===");

            for (int i = 0; i < tab1.length; i++) {
                for (int w = 0; w < tab1[i].length; w++) {
                    tab1[i][w] = (Math.round(((Math.random() * (y - x) + x) * 10.0))) / 10.0;
                    if (tab1[i][w] < 10) {
                        System.out.print((" " + tab1[i][w]) + " | ");
                    } else {
                        System.out.print((tab1[i][w]) + " | ");
                    }
                }
                System.out.println();
            }

            System.out.println();
            System.out.println("====[Macierz druga]====");
            for (int i = 0; i < tab2.length; i++) {
                for (int w = 0; w < tab2[i].length; w++) {
                    tab2[i][w] = (Math.round(((Math.random() * (y - x) + x) * 10.0))) / 10.0;
                    if (tab2[i][w] < 10) {
                        System.out.print((" " + tab2[i][w]) + " | ");
                    } else {
                        System.out.print((tab2[i][w]) + " | ");
                    }
                }
                System.out.println();
            }
            System.out.println();
            int ask;
            do {
                System.out.println();
                System.out.println();

                System.out.println("========[Wybierz operację którą chcesz wykonać:]=========");
                System.out.println("---------------------------------------------------------");
                System.out.println("[1] Odwrócenie macierzy pierwszej lub drugiej");
                System.out.println("[2] Pomnożenie obu macierzy");
                System.out.println("[3] Wyliczenie wyznacznika macierzy pierwszej lub drugiej");
                System.out.println("[4] Podanie statystki");
                System.out.println("[5] Restart macierzy");
                System.out.println("[6] Zakończenie programu");
                System.out.println("---------------------------------------------------------");
                ask = scan.nextInt();
                switch (ask) {
                    case 1:
                        int ask4;
                        do {
                            System.out.println("Wybierz tabelę do odwrócenia: [1]Pierwsza | [2]Druga");
                            ask4 = scan.nextInt();
                            switch (ask4) {
                                case 1:
                                    if (m == n) {
                                        double[][] tabInvert = invert(tab1);
                                        System.out.println();
                                        System.out.println("=====|Wynik odwrócenia pierwszej macierzy|======");
                                        System.out.println();
                                        for (double[] doubles : tabInvert) {
                                            for (j = 0; j < doubles.length; ++j) {
                                                double xInvert;
                                                xInvert = doubles[j];
                                                xInvert *= 100;
                                                xInvert = Math.round(xInvert);
                                                xInvert /= 100;
                                                if (xInvert < 0) {
                                                    System.out.print(xInvert + " | ");
                                                } else {
                                                    System.out.print(" " + xInvert + " | ");
                                                }
                                            }
                                            System.out.println();
                                        }
                                        System.out.println();
                                    } else {
                                        System.out.println();
                                        System.out.print("=====Macierz nie jest kwadratowa!=====");
                                        break;
                                    }
                                    break;
                                case 2:
                                    if (m == n) {
                                        double[][] tabInvert2 = invert(tab1);
                                        System.out.println();
                                        System.out.println("=====|Wynik odwrócenia drugiej macierzy|======");
                                        System.out.println();
                                        for (double[] doubles : tabInvert2) {
                                            for (j = 0; j < doubles.length; ++j) {
                                                double xInvert2;
                                                xInvert2 = doubles[j];
                                                xInvert2 *= 100;
                                                xInvert2 = Math.round(xInvert2);
                                                xInvert2 /= 100;
                                                if (xInvert2 < 0) {
                                                    System.out.print(xInvert2 + " | ");
                                                } else {
                                                    System.out.print(" " + xInvert2 + " | ");
                                                }
                                            }
                                            System.out.println();
                                        }
                                        System.out.println();
                                    } else {
                                        System.out.print("=====Macierz nie jest kwadratowa!=====");
                                        break;
                                    }
                                    break;

                            }
                            if (ask4 != 1 && ask4 != 2) {
                                System.out.println();
                                System.out.println("Podałeś złą opcje!");
                                System.out.println();
                            }
                        } while (ask4 != 1 && ask4 != 2);
                        break;

                    case 2:
                        System.out.println();
                        System.out.println("<[Wynik mnożenia macierz]>");
                        System.out.println("==========================");
                        int[][] tab3 = new int[m][j];
                        for (int i = 0; i < m; i++) {
                            for (int o = 0; o < j; o++) {
                                for (int g = 0; g < n; g++) {
                                    tab3[i][o] += (tab1[i][g] * tab2[g][o]);

                                }
                                if (tab2[i][o] < 10) {
                                    System.out.print((" " + tab3[i][o]) + " | ");
                                } else {
                                    System.out.print(tab3[i][o] + " | ");
                                }


                            }
                            System.out.println();
                        }
                        System.out.println("==========================");
                        System.out.println();

                        break;

                    case 3:
                        int ask3;
                        do {
                            System.out.println("Wybierz tabelę wyliczenia wyznacznika: [1]Pierwsza | [2]Druga");
                            ask3 = scan.nextInt();
                            switch (ask3) {
                                case 1:
                                    double Wyz = tab1[0][0];
                                    double result = 0;
                                    double resultR = 0;
                                    if (tab1.length == 1) {
                                        result = tab1[0][0];
                                        resultR = (Math.round((result) * 10.0)) / 10.0;
                                        System.out.println();
                                        System.out.println("=====|Wyznacznik Pierwszej Macierzy|======");
                                        System.out.println("]= " + resultR);
                                        System.out.println();
                                        System.out.println();
                                    }
                                    if (tab1.length == 2) {
                                        result = tab1[0][0] * tab1[1][1] - tab1[0][1] * tab1[1][0];
                                        resultR = (Math.round((result) * 10.0)) / 10.0;
                                        System.out.println();
                                        System.out.println("=====|Wyznacznik Pierwszej Macierzy|======");
                                        System.out.println("]= " + resultR);
                                        System.out.println();
                                        System.out.println();

                                    }
                                    for (int iw = 0; iw < tab1[0].length; iw++) {
                                        double[][] tabT = new double[tab1.length - 1][tab1[0].length - 1];
                                        for (int jw = 1; jw < tab1.length; jw++) {
                                            for (int kw = 0; kw < tab1[0].length; kw++) {
                                                if (kw < iw) {
                                                    tabT[jw - 1][kw] = tab1[jw][kw];
                                                } else if (kw > iw) {
                                                    tabT[jw - 1][kw - 1] = tab1[jw][kw];
                                                }
                                            }
                                        }
                                        result += tab1[0][iw] * Math.pow(-1, iw) * Wyz;
                                        resultR = (Math.round((result) * 10.0)) / 10.0;
                                    }
                                    System.out.println();
                                    System.out.println("=====|Wyznacznik Pierwszej Macierzy|======");
                                    System.out.println("]= " + resultR);
                                    System.out.println();
                                    System.out.println();

                                    break;
                                case 2:
                                    double Wyz2 = tab1[0][0];
                                    double result2 = 0;
                                    double resultR2 = 0;
                                    if (tab2.length == 1) {
                                        result2 = tab2[0][0];
                                        resultR2 = (Math.round((result2) * 10.0)) / 10.0;
                                        System.out.println();
                                        System.out.println("=====|Wyznacznik Drugiej Macierzy|======");
                                        System.out.println("]= " + resultR2);
                                        System.out.println();
                                        System.out.println();
                                    }
                                    if (tab2.length == 2) {
                                        result2 = tab2[0][0] * tab2[1][1] - tab2[0][1] * tab2[1][0];
                                        resultR2 = (Math.round((result2) * 10.0)) / 10.0;
                                        System.out.println();
                                        System.out.println("=====|Wyznacznik Drugiej Macierzy|======");
                                        System.out.println("]= " + resultR2);
                                        System.out.println();
                                        System.out.println();
                                    }
                                    for (int iw = 0; iw < tab2[0].length; iw++) {
                                        double[][] tabT = new double[tab2.length - 1][tab2[0].length - 1];
                                        for (int jw = 1; jw < tab2.length; jw++) {
                                            for (int kw = 0; kw < tab2[0].length; kw++) {
                                                if (kw < iw) {
                                                    tabT[jw - 1][kw] = tab2[jw][kw];
                                                } else if (kw > iw) {
                                                    tabT[jw - 1][kw - 1] = tab2[jw][kw];
                                                }
                                            }
                                        }
                                        result2 += tab2[0][iw] * Math.pow(-1, iw) * Wyz2;
                                        resultR2 = (Math.round((result2) * 10.0)) / 10.0;
                                    }
                                    System.out.println();
                                    System.out.println("=====|Wyznacznik Drugiej Macierzy|======");
                                    System.out.println("]= " + resultR2);
                                    System.out.println();
                                    System.out.println();

                                    break;
                            }
                            if (ask3 != 1 && ask3 != 2) {
                                System.out.println();
                                System.out.println("Podałeś złą opcje!");
                                System.out.println();
                            }
                        } while (ask3 != 1 && ask3 != 2);
                        break;
                    case 4:
                        int ask2;
                        do {
                            System.out.println("Wybierz tabelę ze statystyką: [1]Pierwsza | [2]Druga");
                            ask2 = scan.nextInt();
                            switch (ask2) {
                                case 1:
                                    System.out.println();
                                    System.out.println("=====|Suma Wartości Pierwszej Macierzy|======");
                                    int suma = 0;
                                    for (double[] doubles : tab1) {
                                        for (int rr = 0; rr < tab1.length; rr++) {
                                            suma += doubles[rr];
                                        }
                                    }
                                    System.out.println("]=" + suma);
                                    //--------------------------------------------------------------------
                                    System.out.println();
                                    System.out.println("=====|Średnia Wartości Pierwszej Macierzy|======");
                                    int srednia = 0;
                                    for (int nr = 0; nr < tab1.length; nr++) {
                                        for (int rr = 0; rr < tab1.length; rr++) {
                                            srednia = suma / tab1.length;
                                        }
                                    }
                                    System.out.println("]=" + srednia);
                                    //--------------------------------------------------------------------
                                    System.out.println();
                                    double Min = tab1[0][0];
                                    for (int ii = 1; ii < tab1.length; ii++) {
                                        for (int ij = 1; ij < tab1[ii].length; ij++) {
                                            if (tab1[ii][ij] < Min) {
                                                Min = tab1[ii][ij];
                                            }
                                        }
                                    }
                                    System.out.println("=====|Minimalna Wartości Pierwszej Macierzy|======");
                                    System.out.println("]=" + Min);
                                    System.out.println();

                                    double Max = tab1[0][0];
                                    for (int ii = 1; ii < tab1.length; ii++) {
                                        for (int ij = 1; ij < tab1[ii].length; ij++) {
                                            if (tab1[ii][ij] > Max) {
                                                Max = tab1[ii][ij];
                                            }
                                        }
                                    }
                                    System.out.println("=====|Maksymalna Wartości Pierwszej Macierzy|======");
                                    System.out.println("]=" + Max);
                                    System.out.println();

                                    break;

                                case 2:
                                    System.out.println();
                                    System.out.println("=====|Suma Wartości Drugiej Macierzy|======");
                                    int suma2 = 0;
                                    for (double[] doubles : tab2) {
                                        for (int rr = 0; rr < tab2.length; rr++) {
                                            suma2 += doubles[rr];
                                        }
                                    }
                                    System.out.println("]=" + suma2);
                                    //--------------------------------------------------------------------
                                    System.out.println();
                                    System.out.println("=====|Średnia Wartości Drugiej Macierzy|======");
                                    int srednia2 = 0;
                                    for (int nr = 0; nr < tab2.length; nr++) {
                                        for (int rr = 0; rr < tab2.length; rr++) {
                                            srednia2 = suma2 / tab2.length;
                                        }
                                    }
                                    System.out.println("]=" + srednia2);
                                    //--------------------------------------------------------------------
                                    System.out.println();
                                    double Min2 = tab2[0][0];
                                    for (int ii = 1; ii < tab2.length; ii++) {
                                        for (int ij = 1; ij < tab2[ii].length; ij++) {
                                            if (tab2[ii][ij] < Min2) {
                                                Min2 = tab2[ii][ij];
                                            }
                                        }
                                    }
                                    System.out.println("=====|Minimalna Wartości Drugiej Macierzy|======");
                                    System.out.println("]=" + Min2);
                                    System.out.println();

                                    double Max2 = tab2[0][0];
                                    for (int ii = 1; ii < tab2.length; ii++) {
                                        for (int ij = 1; ij < tab2[ii].length; ij++) {
                                            if (tab2[ii][ij] > Max2) {
                                                Max2 = tab2[ii][ij];
                                            }
                                        }
                                    }
                                    System.out.println("=====|Maksymalna Wartości Drugiej Macierzy|======");
                                    System.out.println("]=" + Max2);
                                    System.out.println();
                                    break;
                            }
                            if (ask2 != 1 && ask2 != 2) {
                                System.out.println("Podałeś złą opcje!");
                                System.out.println();
                            }
                        } while (ask2 > 2 || ask2 <= 0);


                        break;

                    case 5:
                        break;

                    case 6:
                        System.out.println();
                        System.out.println("Zamykanie programu....");
                        System.exit(1);
                        break;
                }
                if (ask < 1 || ask > 6) {
                    System.out.println();
                    System.out.println("Podałeś złą opcje!");
                    System.out.println();
                }
            } while (ask != 5);
        }
    }
}