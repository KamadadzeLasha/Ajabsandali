package fop.w9store;

public enum ScrewDrive {
//    @Override
//    public abstract String toString();https://kiu_kamadadze.lasha@bitbucket.ase.in.tum.de/scm/KIU22WSCREW/kiu22wscrew-kiu_kamadadze.lasha.git
      SlotDrive{
    @Override
    public String toString() {
        return "ჭანჭიკი";
    }
}, Hex{
        @Override
        public String toString() {
            return "კლუჩი";
        }
    },
    Cross{
        @Override
        public String toString() {
            return "ჯვარი";
        }
    },
    Torx{
        @Override
        public String toString() {
            return "რაცხა";
        }
    };

    @Override
    public abstract String toString();
}
