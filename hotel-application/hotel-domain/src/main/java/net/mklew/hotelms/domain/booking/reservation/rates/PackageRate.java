package net.mklew.hotelms.domain.booking.reservation.rates;

import net.mklew.hotelms.domain.room.Room;
import org.joda.money.Money;

/**
 * @author Marek Lewandowski <marek.m.lewandowski@gmail.com>
 * @since 9/27/12
 *        Time: 12:00 PM
 */
public class PackageRate extends Rate
{
    private Package pack;

    public PackageRate(Money standardPrice, Money upchargeExtraPerson, Money upchargeExtraBed, Room room, Package pack)
    {
        super(standardPrice, upchargeExtraPerson, upchargeExtraBed, room);
        this.pack = pack;
    }

    @Override
    public String getRateName()
    {
        return pack.getPackageName();
    }

    public Package getPackage()
    {
        return pack;
    }

    // hibernate
    private Package getPack()
    {
        return pack;
    }

    // hibernate
    private void setPack(Package pack)
    {
        this.pack = pack;
    }

    // hibernate
    PackageRate()
    {
        // hibernate
    }
}
