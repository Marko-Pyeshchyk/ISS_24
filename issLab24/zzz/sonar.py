import time

def main():
    num = 69
    try:
        while True:
            print(num, flush=True)
            num += 1
            time.sleep(1)  # Attendere un secondo tra le stampe
    except KeyboardInterrupt:
        print("\nInterrotto dall'utente.")

if __name__ == "__main__":
    main()