export interface Room {
    roomId: number;
    roomNumber: number;
    roomPrice: number;
    roomIsEmpty: boolean;
    roomIsClean: boolean;
    roomType: string;
    guestIds: string;
}